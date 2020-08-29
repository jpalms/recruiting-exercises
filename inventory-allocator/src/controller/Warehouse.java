package controller;

import model.Inventory;

import java.util.HashMap;

public class Warehouse {

    private String name;
    private Inventory inv;

    public Warehouse(String name, Inventory inv){
        this.name = name;
        this.inv = inv;
    }

    public String getName() {
        return name;
    }

    public void updateInv(HashMap<String, Integer> product){
        HashMap<String, Integer> pd = this.inv.getProducts();
        pd.putAll(product);
        this.inv = new Inventory(pd);
    }

    public boolean hasProduct(String product){
        return this.inv.hasProduct(product);
    }

    public boolean exactMatch(String product, int amount){
        return this.inv.exactMatch(product, amount);
    }

    public int getAmount(String product){
        return this.inv.getAmount(product);
    }

    public Warehouse shipped(String product, int amount){
        HashMap<String, Integer> pd = this.inv.getProducts();
        pd.replace(product, amount);
        this.inv = new Inventory(pd);
        return this;
    }

    @Override
    public String toString(){
        return "{ name: " + name + ", " + inv.toString() + " }";
    }
}
