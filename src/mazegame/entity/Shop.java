package mazegame.entity;

import java.util.ArrayList;

public class Shop extends Location {
    private ArrayList<Item> itemsForSale;

    // Default constructor
    public Shop() {
        super();
        this.itemsForSale = new ArrayList<Item>();
    }

    // Constructor with description, label, and items
    public Shop(String description, String label) {
        super(description, label);
        this.itemsForSale = new ArrayList<Item>();
    }

    // Add an item to the shop's inventory
    public void addItemForSale(Item item) {
        this.itemsForSale.add(item);
    }

    // Remove an item from the shop's inventory
    public boolean removeItemFromSale(Item item) {
        return this.itemsForSale.remove(item);
    }

    // Get list of items for sale
    public ArrayList<Item> getItemsForSale() {
        return itemsForSale;
    }

    // Display the items available for sale
    public String displayItemsForSale() {
        if (itemsForSale.isEmpty()) {
            return "No items available for sale.";
        }
        StringBuilder itemsDisplay = new StringBuilder("Items for sale:\n");
        for (Item item : itemsForSale) {
            itemsDisplay.append(item.getLabel()).append(" - ").append(item.getDescription()).append("\n");
        }
        return itemsDisplay.toString();
    }

    // Override toString method to display shop details
    @Override
    public String toString() {
        return "**********\n" + this.getLabel() + "\n**********\n" 
            + "Exits found :: " + availableExits() + "\n**********\n" 
            + this.getDescription() + "\n**********\n"
            + displayItemsForSale();
    }
}
