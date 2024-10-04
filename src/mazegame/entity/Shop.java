package mazegame.entity;

import java.util.ArrayList;
import mazegame.entity.items.Item;

public class Shop extends Location {
    private ArrayList<Item> itemsForSale;

    public Shop() {
        super();
        this.itemsForSale = new ArrayList<>();
    }

    public Shop(String description, String label) {
        super(description, label);
        this.itemsForSale = new ArrayList<>();
    }

    public void addItemForSale(Item item) {
        itemsForSale.add(item);
    }

    public void addItemsForSale(Item... items) {
        for (Item item : items) {
            itemsForSale.add(item);
        }
    }

    public boolean removeItemFromSale(Item item) {
        return itemsForSale.remove(item);
    }

    public ArrayList<Item> getItemsForSale() {
        return itemsForSale;
    }

    public String displayItemsForSale() {
        if (itemsForSale.isEmpty()) {
            return "No items available for sale.";
        }

        StringBuilder itemsDisplay = new StringBuilder("Items for sale:\n");

        for (Item item : itemsForSale) {
            itemsDisplay.append("[" + item.getId() + "] " + item.getLabel()).append(" - ").append(item.getDescription()).append("\n");
        }

        return itemsDisplay.toString();
    }

    @Override
    public String toString() {
        StringBuilder exitsDisplay = new StringBuilder("Available exits:\n");
        StringBuilder shopExits = new StringBuilder("Shops nearby:\n");

        for (String key : getExits().keySet()) {
            Exit exit = getExit(key);
            if (exit.getDestination() instanceof Shop) {
                shopExits.append("[").append(key).append("] to ").append(exit.getDestination().getLabel()).append("\n");
            } else {
                exitsDisplay.append("[").append(key).append("] to ").append(exit.getDestination().getLabel()).append("\n");
            }
        }

        return "**********\n" + this.getLabel() + " - " + this.getDescription() + "\n**********\n" + exitsDisplay.toString() +
               (shopExits.length() > "Shops nearby:\n".length() ? shopExits.toString() : "No other shops nearby.\n") + "\n**********\n"  + displayItemsForSale();
    }
}
