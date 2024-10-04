package mazegame.entity;

import java.util.ArrayList;
import java.util.HashMap;

import mazegame.entity.items.Item;

public class Location {
    private HashMap<String, Exit> exits;
    private String description;
    private String label;
    private ArrayList<Item> items;

    public Location() {
        this.items = new ArrayList<>();
        this.exits = new HashMap<>();
    }

    public Location(String description, String label) {
        this();
        this.setDescription(description);
        this.setLabel(label);
    }

    public boolean addExit(String exitLabel, Exit theExit) {
        if (exits.containsKey(exitLabel)) return false;
        exits.put(exitLabel, theExit);
        return true;
    }

    public Exit getExit(String exitLabel) {
        return exits.get(exitLabel);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean containsExit(String exitLabel) {
        return exits.containsKey(exitLabel);
    }

    public String availableExits() {
        StringBuilder returnMsg = new StringBuilder();
        for (String label : this.exits.keySet()) {
            returnMsg.append("[").append(label).append("] ");
        }
        return returnMsg.toString();
    }

    public HashMap<String, Exit> getExits() {
        return exits;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    
    public void addItems(Item... newItems) {
        for (Item item : newItems) {
            items.add(item);
        }
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String displayItems() {
        if (items.isEmpty()) {
            return "No items found at this location.";
        }

        StringBuilder itemDisplay = new StringBuilder("Items available at this location:\n");
        for (Item item : items) {
            itemDisplay.append("[" + item.getId() + "]" + item.getLabel()).append(" - ").append(item.getDescription()).append("\n");
        }

        return itemDisplay.toString();
    }

    @Override
    public String toString() {
        StringBuilder exitsDisplay = new StringBuilder("Available exits:\n");
        StringBuilder shopExits = new StringBuilder("Shops nearby:\n");

        for (String key : this.exits.keySet()) {
            Exit exit = exits.get(key);
            if (exit.getDestination() instanceof Shop) {
                shopExits.append("[").append(key).append("] to ").append(exit.getDestination().getLabel()).append("\n");
            } else {
                exitsDisplay.append("[").append(key).append("] to ").append(exit.getDestination().getLabel()).append("\n");
            }
        }

        return "**********\n" + this.label + " - " + this.description + "\n**********\n" + exitsDisplay.toString() +
               (shopExits.length() > "Shops nearby:\n".length() ? shopExits.toString() : "No shops nearby.\n") +
                "\n**********\n" + displayItems();
    }
}
