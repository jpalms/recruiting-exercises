package controller;

import model.Inventory;
import model.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;

public class Shipping {

    private ArrayList<Warehouse> shipping;

    public Shipping(){
        this.shipping = new ArrayList<>();
    }

    /**
     * Description: Adds a new item to be shipped to the current shipping order
     *
     * @param warehouse - warehouse to that the item is coming from
     * @param product - Item to be shipped
     */
    public void update(String warehouse, HashMap<String, Integer> product){
        boolean exists = false;
        int index = 0;

        // Determines if the warehouse is already being shipped from
        for (int i = 0; i < shipping.size(); i++){
            if(shipping.get(i).getName().equals(warehouse)){
                exists = true;
                index = i;
                break;
            }
        }

        // if true then update items being shipped to that warehouse
        // otherwise add that warehouse and item to the shipping order
        if(exists){
            shipping.get(index).updateInv(product);
        } else{
            shipping.add(new Warehouse(warehouse, new Inventory(product)));
        }
    }

    /**
     * Description: String representation of items to be shipped
     *
     * @return - String
     */
    @Override
    public String toString() {
        return this.shipping.toString();
    }
}
