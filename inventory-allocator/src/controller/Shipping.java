package controller;

import model.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Shipping {

    private ArrayList<Warehouse> shipping;

    public Shipping(){
        this.shipping = new ArrayList<>();
    }

    public void update(String wharehouse, HashMap<String, Integer> product){
        boolean exists = false;
        int index = 0;

        for (int i = 0; i < shipping.size(); i++){
            if(shipping.get(i).getName().equals(wharehouse)){
                exists = true;
                index = i;
                break;
            }
        }

        if(exists){
            shipping.get(index).updateInv(product);
        } else{
            shipping.add(new Warehouse(wharehouse, new Inventory(product)));
        }
    }

    @Override
    public String toString() {
        return this.shipping.toString();
    }
}
