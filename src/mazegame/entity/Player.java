package mazegame.entity;

import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;
import mazegame.entity.items.Armor;

import java.util.Scanner;

public class Player extends Character implements Attacker {
    
    private Location currentLocation;
    private Inventory inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private double weight;
    private Scanner scanner;
    
    public Player() {
        this.inventory = new Inventory();
        setDefaultStats();
        addDefaultWeapon();
        this.scanner = new Scanner(System.in);
    }

    public Player(String name) {
        super(name);
        this.inventory = new Inventory();
        setDefaultStats();
        addDefaultWeapon();
        this.scanner = new Scanner(System.in);
    }

    private void setDefaultStats() {
        setAgility(10);
        setLifePoints(10);
        setStrength(10);
        this.weight = 10;
    }

    private void addDefaultWeapon() {
        Weapon defaultWeapon = new Weapon("Basic Sword", "A basic sword to start your journey", 3.0, 10.0, 5);
        addItemToInventory(defaultWeapon);
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
        this.weight -= item.getWeight();
    }

    public void removeItemFromInventory(Item item) {
        this.inventory.removeItem(item);
        this.weight += item.getWeight();
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

    public double getWeight() {
        return weight;
    }
    
    public void attack(Attacker target) {
        target.takeDamage(this.getStrength());
    }

    public void takeDamage(int damage) {
        setLifePoints(getLifePoints() - damage);
    }
    
    public String getInput() {
        System.out.println("Enter your action (ATTACK, DEFEND, FLEE): ");
        return scanner.nextLine().trim().toLowerCase();
    }
}
