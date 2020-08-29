import controller.Shipping;
import controller.Warehouse;
import model.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Allocator {

    public static void main(String []args){
        Scanner input = new Scanner(System.in);

        String map = input.nextLine();
        String storage = input.nextLine();

        map = map.substring(2);

        HashMap<String, Integer> find = new HashMap<String, Integer>();

        while(map.contains(":")){
            int colon = map.indexOf(':');
            String name = map.substring(0, colon);
            map = map.substring(colon + 2);
            int endNum = map.indexOf(',');
            if(endNum < 0){
                endNum = map.indexOf(' ');
            }
            int amount = Integer.parseInt(map.substring(0, endNum));
            find.put(name, amount);

            if(map.contains(",")) {
                map = map.substring(map.indexOf(',') + 2);
            } else{
                map = "";
            }
        }

        ArrayList<Warehouse> warehouse = new ArrayList<>();
        while(storage.contains("name:")) {
            storage = storage.substring(storage.indexOf("name:"));
            warehouse.add(buildWharehouse(storage));

            storage = storage.substring(storage.indexOf("name:") + 1);
        }

        Shipping shipping = new Shipping();

        boolean exists = false;
        for (String product: find.keySet()) {
            exists = false;
            Warehouse exact = null;
            ArrayList<Integer> matches = new ArrayList<>();

            int sum = 0;

            for (int i = 0; i < warehouse.size(); i++){
                Warehouse w = warehouse.get(i);
                if(w.hasProduct(product)){
                    exists = true;

                    matches.add(i);

                    if(w.exactMatch(product, find.get(product))){
                        exact = w;
                        break;
                    }
                    sum += w.getAmount(product);
                }
            }

            if(!exists){
                break;
            }
            HashMap<String, Integer> tmp = new HashMap<>();
            tmp.put(product, find.get(product));

            if(exact != null){
                shipping.update(exact.getName(), tmp);
                int index = matches.get(matches.size()-1);
                warehouse.set(index, warehouse.get(index).shipped(product, 0));
            } else{
                tmp = new HashMap<>();

                if(sum < find.get(product)){
                    exists = false;
                    break;
                }

                int quota = find.get(product);

                // split accross multiple warehouses
                for (int i = 0; i < matches.size(); i++) {
                    int index = matches.get(i);
                    Warehouse w = warehouse.get(index);

                    int stored = w.getAmount(product);

                    if(stored > quota){
                        tmp.put(product, quota);
                        shipping.update(w.getName(), tmp);
                        warehouse.set(index, w.shipped(product, stored - quota));
                        break;
                    } else{
                        quota -= stored;
                        tmp.put(product, stored);
                        shipping.update(w.getName(), tmp);
                        warehouse.set(index, w.shipped(product, 0));
                    }
                }
            }
        }

        if(exists){
            System.out.println(shipping.toString());
        } else{
            System.out.println("[]");
        }
    }

    public static Warehouse buildWharehouse(String storage){
        int index = storage.indexOf(":");
        String name = storage.substring(index + 2, storage.indexOf(","));

        storage = storage.substring(storage.indexOf("inventory:") + 13, storage.indexOf("}"));

        HashMap<String, Integer> items = new HashMap<String, Integer>();

        while(storage.contains(":")){
            int colon = storage.indexOf(':');
            String food = storage.substring(0, colon);
            storage = storage.substring(colon + 2);
            int endNum = storage.indexOf(',');
            if(endNum < 0){
                endNum = storage.indexOf(' ');
            }
            int amount = Integer.parseInt(storage.substring(0, endNum));

            items.put(food, amount);

            if(storage.contains(",")) {
                storage = storage.substring(storage.indexOf(',') + 2);
            } else{
                storage = "";
            }
        }

        return new Warehouse(name, new Inventory(items));
    }
}
