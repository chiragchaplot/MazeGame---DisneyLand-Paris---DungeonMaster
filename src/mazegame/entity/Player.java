package mazegame.entity;

import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;

public class Player extends Character {
    
    private Location currentLocation;
    private Inventory inventory;

    public Player() {
        this.inventory = new Inventory();
        addDefaultWeapon();
    }

    public Player(String name) {
        super(name);
        this.inventory = new Inventory();
        addDefaultWeapon();
    }

    private void addDefaultWeapon() {
        Weapon defaultWeapon = new Weapon("Basic Sword", "A basic sword to start your journey", 3.0, 10.0, 5);
        this.inventory.addItem(defaultWeapon);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        this.inventory.removeItem(item);
    }
}
