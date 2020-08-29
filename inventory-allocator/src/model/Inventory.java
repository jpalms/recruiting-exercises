package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> products;

    public Inventory(HashMap<String, Integer> products){
        this.products = products;
    }

    @Override
    public String toString(){
        String result = "";

        for (String p: products.keySet()) {
            result += p + ": " + products.get(p);
            result += ", ";
        }
        return "inventory: { " + result + " }";
    }
}
