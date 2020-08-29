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
            int amount = Integer.parseInt(map.substring(colon + 2, colon + 3));

            find.put(name, amount);

            if(map.contains(",")) {
                map = map.substring(map.indexOf(',') + 2);
            } else{
                map = "";
            }
        }

        System.out.println(find.toString());

        storage = storage.substring(4);

        ArrayList<Warehouse> warehouse = new ArrayList<>();
        while(storage.length() > 3){
            warehouse.add(buildWharehouse(storage));

            storage = storage.substring(storage.indexOf('}') + 4);
        }

        System.out.println(warehouse.toString());
    }

    public static Warehouse buildWharehouse(String storage){
        int index = storage.indexOf(":");
        String name = storage.substring(index + 2, storage.indexOf(","));

        storage = storage.substring(storage.indexOf("inventory:") + 13, storage.indexOf("}"));

        HashMap<String, Integer> items = new HashMap<String, Integer>();

        while(storage.contains(":")){
            int colon = storage.indexOf(':');
            String food = storage.substring(0, colon);
            int amount = Integer.parseInt(storage.substring(colon + 2, colon + 3));

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
