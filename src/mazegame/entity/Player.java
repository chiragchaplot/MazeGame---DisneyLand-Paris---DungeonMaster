package mazegame.entity;

import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;
import mazegame.entity.items.Armor;

public class Player extends Character {
    
    private Location currentLocation;
    private Inventory inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

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
    
    public Item getItemById(int id) {
        return inventory.getItemById(id);
    }
    
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
    }

    public void unequipItem(Item item) {
        if (item instanceof Weapon && this.equippedWeapon != null && this.equippedWeapon.equals(item)) {
            this.equippedWeapon = null;
        } else if (item instanceof Armor && this.equippedArmor != null && this.equippedArmor.equals(item)) {
            this.equippedArmor = null;
        }
    }

    public boolean isEquipped(Item item) {
        return (item.equals(this.equippedWeapon) || item.equals(this.equippedArmor));
    }

}
