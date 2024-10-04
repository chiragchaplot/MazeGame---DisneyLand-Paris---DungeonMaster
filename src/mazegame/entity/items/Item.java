package mazegame.entity.items;

public class Item {
    private static int idCounter = 0;  // Static counter to assign unique IDs to each item
    private int id;                    // Unique ID for each item
    private String label;              // Name of the item
    private String description;        // Description of the item
    private double weight;             // Weight of the item
    private double value;              // Value or cost of the item

    // Default constructor
    public Item() {
        this.id = ++idCounter;  // Increment and assign unique ID
    }

    // Constructor with parameters
    public Item(String label, String description, double weight, double value) {
        this();  // Call the default constructor to increment ID
        this.label = label;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    // Getters and setters for item attributes
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

    // toString method to represent the item as a string
    @Override
    public String toString() {
        return "Item ID: " + id + "\n" +
               "Item: " + label + "\n" +
               "Description: " + description + "\n" +
               "Weight: " + weight + "\n" +
               "Value: " + value + "\n";
    }
}
