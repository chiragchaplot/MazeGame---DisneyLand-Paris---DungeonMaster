package mazegame.entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import mazegame.entity.items.*;

public class NonPlayableCharacter extends Character implements Attacker {
    private boolean hostile;
    private HashMap<String, String> conversationListMap;
    private Weapon equippedWeapon;
    private List<Item> items;

    public NonPlayableCharacter(String name) {
        super(name);
        this.conversationListMap = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public HashMap<String, String> getConversationList() {
        return conversationListMap;
    }

    public void setConversationListMap(HashMap<String, String> conversationListMap) {
        this.conversationListMap = conversationListMap;
    }

    public String talk(String playerInput) {
        if (conversationListMap.containsKey(playerInput)) {
            return conversationListMap.get(playerInput);
        } else {
            return "The NPC has nothing to say about that.";
        }
    }

    public String getStatus() {
        return getName() + " [Strength: " + getStrength() + ", Agility: " + getAgility() + ", Life Points: " + getLifePoints() + "]";
    }

    @Override
    public String toString() {
        return getName() + " - " + (hostile ? "Hostile" : "Friendly");
    }
    
    public void attack(Attacker target) {
        target.takeDamage(this.getStrength());
    }

    public void takeDamage(int damage) {
        setLifePoints(getLifePoints() - damage);
    }
    
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }
    
    public Item getItemById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
}
