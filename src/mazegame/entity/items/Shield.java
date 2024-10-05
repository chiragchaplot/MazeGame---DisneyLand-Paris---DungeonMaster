package mazegame.entity.items;

import java.util.ArrayList;

import mazegame.entity.RandomNumberGenerator;

public class Shield extends Item {
    private int defense;

    public Shield(String label, String description, double weight, double value, int defense) {
        super(label, description, weight, value);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return super.toString() + "Defense: " + defense + "\n";
    }

    // Static method to return a list of shields
    public static ArrayList<Shield> createShieldList() {
        ArrayList<Shield> shields = new ArrayList<>();
        RandomNumberGenerator gen = new RandomNumberGenerator();
        shields.add(new Shield("Wooden Shield", "A wooden shield", 5.0, 2.0, gen.generateRandomInRange(0, 5)));
        shields.add(new Shield("Steel Shield", "A steel shield", 15.0, 20.0, gen.generateRandomInRange(0, 5)));
        shields.add(new Shield("Buckler", "A small buckler shield", 5.0, 15.0, gen.generateRandomInRange(0, 5)));
        shields.add(new Shield("Kite Shield", "A large kite-shaped shield", 10.0, 25.0, gen.generateRandomInRange(0, 5)));
        shields.add(new Shield("Tower Shield", "A massive shield", 20.0, 50.0, gen.generateRandomInRange(0, 5)));
        return shields;
    }
}
