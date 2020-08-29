package model;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> products;

    public Inventory(HashMap<String, Integer> products){
        this.products = products;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public boolean hasProduct(String name){
        return this.products.containsKey(name);
    }

    public boolean exactMatch(String product, int amount){
        return this.products.containsKey(product) && this.products.get(product) == amount;
    }

    public int getAmount(String product){
        return this.products.get(product);
    }

    @Override
    public String toString(){
        String result = "";

        for (String p: products.keySet()) {
            result += p + ": " + products.get(p);
            result += ", ";
        }

        // remove extra comma
        result = result.substring(0, result.length()-2);

        return "inventory: { " + result + " }";
    }
}
