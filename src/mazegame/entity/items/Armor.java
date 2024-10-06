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
        armors.add(new Armor("Padded", "A padded armor", gen.generateRandomInRange(0, 5), 5.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Leather", "A leather armor", gen.generateRandomInRange(0, 5), 10.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Chainmail", "A heavy chainmail armor", gen.generateRandomInRange(0, 5), 30.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Plate Armor", "A suit of plate armor", gen.generateRandomInRange(0, 5), 50.0, gen.generateRandomInRange(0, 2)));
        armors.add(new Armor("Dragon Scale", "Armor made from dragon scales", gen.generateRandomInRange(0, 5), 100.0, gen.generateRandomInRange(0, 2)));
        return armors;
    }
}
