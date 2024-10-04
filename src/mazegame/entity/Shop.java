package mazegame.entity;

import java.util.ArrayList;
import mazegame.entity.items.*;

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

 // Display the items available for sale, categorized by item type
 // Display the items available for sale, categorized by item type and including item ID
    public String displayItemsForSale() {
        if (itemsForSale.isEmpty()) {
            return "No items available for sale.";
        }

        StringBuilder itemsDisplay = new StringBuilder("Items for sale:\n");

        // Categorize items into different types
        ArrayList<Item> weapons = new ArrayList<>();
        ArrayList<Item> armors = new ArrayList<>();
        ArrayList<Item> shields = new ArrayList<>();
        ArrayList<Item> potions = new ArrayList<>();
        ArrayList<Item> others = new ArrayList<>();

        // Sort items into their respective categories
        for (Item item : itemsForSale) {
            if (item instanceof Weapon) {
                weapons.add(item);
            } else if (item instanceof Armor) {
                armors.add(item);
            } else if (item instanceof Shield) {
                shields.add(item);
            } else if (item instanceof Potion) {
                potions.add(item);
            } else {
                others.add(item); // For other types of items
            }
        }

        // Display weapons
        if (!weapons.isEmpty()) {
            itemsDisplay.append("\nWeapons:\n");
            for (Item weapon : weapons) {
                itemsDisplay.append("[").append(weapon.getId()).append("] ")
                            .append(weapon.getLabel()).append(" - ")
                            .append(weapon.getDescription()).append("\n");
            }
        }

        // Display armors
        if (!armors.isEmpty()) {
            itemsDisplay.append("\nArmors:\n");
            for (Item armor : armors) {
                itemsDisplay.append("[").append(armor.getId()).append("] ")
                            .append(armor.getLabel()).append(" - ")
                            .append(armor.getDescription()).append("\n");
            }
        }

        // Display shields
        if (!shields.isEmpty()) {
            itemsDisplay.append("\nShields:\n");
            for (Item shield : shields) {
                itemsDisplay.append("[").append(shield.getId()).append("] ")
                            .append(shield.getLabel()).append(" - ")
                            .append(shield.getDescription()).append("\n");
            }
        }

        // Display potions
        if (!potions.isEmpty()) {
            itemsDisplay.append("\nPotions:\n");
            for (Item potion : potions) {
                itemsDisplay.append("[").append(potion.getId()).append("] ")
                            .append(potion.getLabel()).append(" - ")
                            .append(potion.getDescription()).append("\n");
            }
        }

        // Display other items if there are any
        if (!others.isEmpty()) {
            itemsDisplay.append("\nOther Items:\n");
            for (Item other : others) {
                itemsDisplay.append("[").append(other.getId()).append("] ")
                            .append(other.getLabel()).append(" - ")
                            .append(other.getDescription()).append("\n");
            }
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
