package mazegame.entity.items;

import java.util.ArrayList;

import mazegame.entity.RandomNumberGenerator;

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

    public static ArrayList<Weapon> createWeaponList() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        RandomNumberGenerator gen = new RandomNumberGenerator();
        weapons.add(new Weapon("Sword", "A sharp steel sword", gen.generateRandomInRange(0, 5), 10.0, gen.generateRandomInRange(1, 5)));
        weapons.add(new Weapon("Longsword", "A balanced and sharp longsword", gen.generateRandomInRange(0, 5), 15.0, gen.generateRandomInRange(1, 5)));
        weapons.add(new Weapon("Warhammer", "A massive hammer for crushing enemies", gen.generateRandomInRange(0, 5), 18.0, gen.generateRandomInRange(1, 5)));
        weapons.add(new Weapon("Bow", "A long-range bow with sharp arrows", gen.generateRandomInRange(0, 5), 12.0, gen.generateRandomInRange(1, 5)));
        weapons.add(new Weapon("Mace", "A spiked club for close combat", gen.generateRandomInRange(0, 5), 10.0, gen.generateRandomInRange(1, 5)));
        weapons.add(new Weapon("Katana", "A swift and deadly Japanese sword", gen.generateRandomInRange(0, 5), 20.0, gen.generateRandomInRange(1, 5)));
        return weapons;
    }
}
