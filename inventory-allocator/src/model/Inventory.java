package model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> storage;

    public Inventory(ArrayList<Product> storage){
        this.storage = storage;
    }

    @Override
    public String toString(){
        String products = "";

        for (int i = 0; i < storage.size(); i++) {
            products += storage.get(i).toString();
            if(i != storage.size() - 1){
                products += ", ";
            }
        }
        return "inventory: { " + products + " }";
    }
}
