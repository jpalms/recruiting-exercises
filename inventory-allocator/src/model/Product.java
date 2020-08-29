package model;

public class Product {

    private String name;
    private int amount;

    public Product(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return name + ": " + amount;
    }
}
