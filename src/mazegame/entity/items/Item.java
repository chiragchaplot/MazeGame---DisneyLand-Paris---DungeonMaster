package mazegame.entity.items;

public class Item {
    private static int idCounter = 0;
    private int id;
    private String label;
    private String description;
    private double weight;
    private double value;

    public Item() {
        this.id = ++idCounter;
    }

    public Item(String label, String description, double weight, double value) {
        this();
        this.label = label;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + "\n" +
               "Item: " + label + "\n" +
               "Description: " + description + "\n" +
               "Weight: " + weight + "\n" +
               "Value: " + value + "\n";
    }
}
