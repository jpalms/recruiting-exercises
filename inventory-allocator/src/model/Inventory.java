package model;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> products;

    public Inventory(HashMap<String, Integer> products){
        this.products = products;
    }

    /**
     * Description: gets the items in Inventory
     *
     * @return - Hashmap of stored items
     */
    public HashMap<String, Integer> getProducts() {
        return products;
    }

    /**
     * Description: Determines if a item exists in Inventory
     *
     * @param name - item to be checked
     * @return - true if item exists, false otherwise
     */
    public boolean hasProduct(String name){
        return this.products.containsKey(name);
    }

    /**
     * Description: Determines if a item can be shipped from one warehouse
     *
     * @param product - item to be checked
     * @param amount - amount to be shipped
     * @return - true if can be shipped from one, false otherwise
     */
    public boolean canShipFromOne(String product, int amount){
        return this.products.containsKey(product) && this.products.get(product) >= amount;
    }

    /**
     *
     * @param product
     * @return
     */
    public int getAmount(String product){
        return this.products.get(product);
    }

    /**
     * Description: String representation of Inventory
     *
     * @return - String
     */
    @Override
    public String toString(){
        String result = "";

        for (String p: products.keySet()) {
            result += p + ": " + products.get(p);
            result += ", ";
        }

        // remove extra comma
        result = result.substring(0, result.length()-2);

        return ": { " + result + " }";
    }
}
