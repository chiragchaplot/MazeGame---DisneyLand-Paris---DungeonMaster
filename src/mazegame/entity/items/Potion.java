package mazegame.entity.items;

import java.util.ArrayList;

import mazegame.entity.RandomNumberGenerator;

public class Potion extends Item {
    private int restorePoints;

    public Potion(String label, String description, double weight, double value, int restorePoints) {
        super(label, description, weight, value);
        this.restorePoints = restorePoints;
    }

    public int getRestorePoints() {
        return restorePoints;
    }

    public void setRestorePoints(int restorePoints) {
        this.restorePoints = restorePoints;
    }

    @Override
    public String toString() {
        return super.toString() + "Restores: " + restorePoints + " points\n";
    }

    // Static method to return a list of potions
    public static ArrayList<Potion> createPotionList() {
        ArrayList<Potion> potions = new ArrayList<>();
        RandomNumberGenerator gen = new RandomNumberGenerator();
        potions.add(new Potion("Health Potion", "Restores health", 0.5, 5.0, gen.generateRandomInRange(0, 1)));
        potions.add(new Potion("Stamina Potion", "Restores stamina", 0.5, 7.0, gen.generateRandomInRange(0, 1)));
        potions.add(new Potion("Mana Potion", "Restores magical energy", 0.5, 10.0, gen.generateRandomInRange(0, 1)));
        potions.add(new Potion("Antidote", "Cures poison", 0.3, 8.0, gen.generateRandomInRange(0, 1)));
        potions.add(new Potion("Strength Potion", "Boosts strength temporarily", 0.4, 20.0, gen.generateRandomInRange(0, 1)));
        return potions;
    }
}
