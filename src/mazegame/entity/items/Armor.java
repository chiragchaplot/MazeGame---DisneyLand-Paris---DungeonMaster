package mazegame.entity.items;

import java.util.ArrayList;

import mazegame.entity.RandomNumberGenerator;

public class Armor extends Item {
    private int defenseBonus;

    public Armor(String label, String description, double weight, double value, int defenseBonus) {
        super(label, description, weight, value);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }

    @Override
    public String toString() {
        return super.toString() + "Defense Bonus: " + defenseBonus + "\n";
    }

    // Static method to return a list of armors
    public static ArrayList<Armor> createArmorList() {
        ArrayList<Armor> armors = new ArrayList<>();
        RandomNumberGenerator gen = new RandomNumberGenerator();
        armors.add(new Armor("Padded", "A padded armor", 4.0, 5.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Leather", "A leather armor", 6.0, 10.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Chainmail", "A heavy chainmail armor", 10.0, 30.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Plate Armor", "A suit of plate armor", 15.0, 50.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Dragon Scale", "Armor made from dragon scales", 12.0, 100.0, gen.generateRandomInRange(0, 2)));
        return armors;
    }
}
