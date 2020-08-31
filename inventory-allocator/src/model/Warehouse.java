package model;

import java.util.HashMap;

public class Warehouse {

    private String name;
    private Inventory inv;

    public Warehouse(String name, Inventory inv){
        this.name = name;
        this.inv = inv;
    }

    /**
     * Description: gets the name of the Warehouse
     *
     * @return - name of the warehouse
     */
    public String getName() {
        return name;
    }

    /**
     * Description: Updates Inventory with new products
     *
     * @param product - items to be added
     */
    public void updateInv(HashMap<String, Integer> product){
        HashMap<String, Integer> pd = this.inv.getProducts();
        pd.putAll(product);
        this.inv = new Inventory(pd);
    }

    /**
     * Description: Determines if a items exists in Inventory
     *
     * @param product - item to be checked
     * @return - true if found, false otherwise
     */
    public boolean hasProduct(String product){
        return this.inv.hasProduct(product);
    }

    /**
     * Description: Determines if
     * @param product
     * @param amount
     * @return
     */
    public boolean canShipFromOne(String product, int amount){
        return this.inv.canShipFromOne(product, amount);
    }

    /**
     * Pre-condition: product exists in Inventory
     * Description: get the amount left for a specified item
     *
     * @param product - item to check in Inventory
     * @return - amount of that item left
     */
    public int getAmount(String product){
        return this.inv.getAmount(product);
    }

    /**
     * Pre-condition: product exists in Inventory
     * Description: Updates Warehouse with the inventory left after shipping an item
     *
     * @param product - item shipped
     * @param amount - amount of shipped item left
     *
     * @return - new Warehouse with updated amount
     */
    public Warehouse shipped(String product, int amount){
        HashMap<String, Integer> pd = this.inv.getProducts();
        pd.replace(product, amount);
        this.inv = new Inventory(pd);
        return this;
    }

    /**
     * Description: String presentation of a Warehouse
     *
     * @return - String
     */
    @Override
    public String toString(){
        return "{ " + name + inv.toString() + " }";
    }
}
