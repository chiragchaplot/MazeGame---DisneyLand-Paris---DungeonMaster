package mazegame.entity.items;

import java.util.ArrayList;

public class Weapon extends Item {
    private int damage;

    public Weapon(String label, String description, double weight, double value, int damage) {
        super(label, description, weight, value);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return super.toString() + "Damage: " + damage + "\n";
    }

    // Static method to return a list of weapons
    public static ArrayList<Weapon> createWeaponList() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon("Sword", "A sharp steel sword", 3.0, 10.0, 10));
        weapons.add(new Weapon("Longsword", "A balanced and sharp longsword", 5.0, 15.0, 12));
        weapons.add(new Weapon("Warhammer", "A massive hammer for crushing enemies", 7.0, 18.0, 16));
        weapons.add(new Weapon("Bow", "A long-range bow with sharp arrows", 2.5, 12.0, 8));
        weapons.add(new Weapon("Mace", "A spiked club for close combat", 4.0, 10.0, 10));
        weapons.add(new Weapon("Katana", "A swift and deadly Japanese sword", 3.5, 20.0, 14));
        return weapons;
    }
}
