package controller;

import model.Inventory;

public class Warehouse {

    private String name;
    private Inventory inv;

    public Warehouse(String name, Inventory inv){
        this.name = name;
        this.inv = inv;
    }

    @Override
    public String toString(){
        return "{ name: " + name + ", " + inv.toString() + " }";
    }
}
