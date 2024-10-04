package mazegame.entity;

import mazegame.entity.items.Item;
import java.util.ArrayList;

public class Player extends Character {
    
    private Location currentLocation;
    private Inventory inventory;

    // Default constructor
    public Player() {
        this.inventory = new Inventory();
    }

    // Constructor with name
    public Player(String name) {
        super(name);
        this.inventory = new Inventory();
    }

    // Getters and setters
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    // Get the player's inventory
    public Inventory getInventory() {
        return inventory;
    }

    // Add an item to the player's inventory
    public void addItemToInventory(Item item) {
        this.inventory.addItem(item);
    }

    // Remove an item from the player's inventory
    public void removeItemFromInventory(Item item) {
        this.inventory.removeItem(item);
    }
}
