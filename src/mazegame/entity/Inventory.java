package mazegame.entity;

import mazegame.entity.items.Item;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public <T extends Item> T getItemByType(Class<T> itemType) {
        for (Item item : items) {
            if (itemType.isInstance(item)) {
                return itemType.cast(item);
            }
        }
        return null;
    }
}
