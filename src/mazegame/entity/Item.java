package mazegame.entity;

public class Item {
    private String label;        // Name of the item
    private String description;  // Description of the item
    private double weight;       // Weight of the item
    private double value;        // Value or cost of the item

    // Default constructor
    public Item() { }

    // Constructor with parameters
    public Item(String label, String description, double weight, double value) {
        this.label = label;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    // Getters and setters for item attributes
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

    // toString method to represent the item as a string
    @Override
    public String toString() {
        return "Item: " + label + "\n" +
               "Description: " + description + "\n" +
               "Weight: " + weight + "\n" +
               "Value: " + value + "\n";
    }
}
