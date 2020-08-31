import controller.Shipping;
import model.Warehouse;
import model.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Allocator {

    /**
     * Description: Takes in list of items to ship and warehouse information and outputs the Shipping order
     *
     * @param args - to lines of input
     */
    public static void main(String []args){
        // read in input
        Scanner input = new Scanner(System.in);

        String map = input.nextLine();
        String storage = input.nextLine();

        // start input at first item
        map = map.substring(map.indexOf(' ') + 1);

        HashMap<String, Integer> find = parseInventory(map);

        // parse second input into a list of wharehouses
        ArrayList<Warehouse> warehouse = new ArrayList<>();
        // loop while there is a unparsed storage
        while(storage.contains("name:")) {
            storage = storage.substring(storage.indexOf("name:"));
            warehouse.add(buildWharehouse(storage));

            // makes read name unrecongized so the next on can be read
            storage = storage.substring(storage.indexOf("name:") + 1);
        }

        printOrder(find, warehouse);
    }

    /**
     * Description: Print a Shipping order
     *
     * @param find - items to ship
     * @param warehouse - warehouses to ship from
     */
    private static void printOrder(HashMap<String, Integer> find, ArrayList<Warehouse> warehouse){
        Shipping shipping = new Shipping();
        // used to determine if an order is possible
        boolean exists = false;
        for (String product: find.keySet()) {
            exists = false;
            Warehouse exact = null;
            ArrayList<Integer> matches = new ArrayList<>();

            int sum = 0;

            // determine if the item exists in an warehouse
            for (int i = 0; i < warehouse.size(); i++){
                Warehouse w = warehouse.get(i);
                if(w.hasProduct(product)){
                    exists = true;

                    matches.add(i);

                    // gets first exact match, which should be the cheapest
                    if(w.canShipFromOne(product, find.get(product))){
                        exact = w;
                        break;
                    }
                    sum += w.getAmount(product);
                }
            }

            // end if a item can't be found
            if(!exists){
                break;
            }
            HashMap<String, Integer> tmp = new HashMap<>();
            tmp.put(product, find.get(product));

            int quota = find.get(product);

            // if an exact match has been found
            // otherwise look for a multiple warehouse order
            if(exact != null){
                // adds match to shipping order and updates warehouse storage
                shipping.update(exact.getName(), tmp);
                int index = matches.get(matches.size()-1);

                Warehouse w = warehouse.get(index);
                warehouse.set(index, w.shipped(product, w.getAmount(product) - quota));
            } else{
                tmp = new HashMap<>();

                // determines if a multiple warehouse order is possible
                if(sum < find.get(product)){
                    exists = false;
                    break;
                }

                // split accross multiple warehouses
                for (int i = 0; i < matches.size(); i++) {
                    int index = matches.get(i);
                    Warehouse w = warehouse.get(index);

                    int stored = w.getAmount(product);

                    if(stored > quota){
                        // warehouse still has some items left
                        tmp.put(product, quota);
                        shipping.update(w.getName(), tmp);
                        warehouse.set(index, w.shipped(product, stored - quota));
                        break;
                    } else{
                        // empty the warehouse of item
                        quota -= stored;
                        tmp.put(product, stored);
                        shipping.update(w.getName(), tmp);
                        warehouse.set(index, w.shipped(product, 0));
                    }
                }
            }
        }

        // if there is a solution print the results
        // otherwise print blank
        if(exists){
            System.out.println(shipping.toString());
        } else{
            System.out.println("[]");
        }

    }

    /**
     * Pre-con: A warehouse must have a least 1 item in Inventory
     * Description: Parses a substring of input to build a single warehouse
     *
     * @param storage - substring to parse
     * @return - Warehouse object with its inventory contents
     */
    private static Warehouse buildWharehouse(String storage){
        int index = storage.indexOf(":");
        String name = storage.substring(index + 2, storage.indexOf(","));

        storage = storage.substring(storage.indexOf("inventory:") + 13, storage.indexOf("}"));

        HashMap<String, Integer> items = parseInventory(storage);

        return new Warehouse(name, new Inventory(items));
    }

    /**
     * Pre-con: At least one item
     * Description: Parses a substring of input to build a Hashmap of the Inventory
     *
     * @param storage - substring to parse
     * @return - Hashmap of items in storage
     */
    private static HashMap<String, Integer> parseInventory(String storage){

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

        return items;
    }
}
