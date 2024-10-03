package mazegame.entity;

import java.util.HashMap;

import mazegame.entity.Character;

public class NonPlayableCharacter extends Character {

    private boolean hostile;
    private HashMap<String, String> conversationListMap;

    // Constructor to initialize the NPC with name
    public NonPlayableCharacter(String name) {
        super(name);  // Calling the parent class constructor
        this.conversationListMap = new HashMap<>();
    }

    // Getter and Setter methods for hostile attribute
    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public HashMap<String, String> getConversationListMap() {
        return conversationListMap;
    }

    public void setConversationListMap(HashMap<String, String> conversationListMap) {
        this.conversationListMap = conversationListMap;
    }

    // Method for NPC interaction (talk)
    public String talk(String playerInput) {
        if (conversationListMap.containsKey(playerInput)) {
            return conversationListMap.get(playerInput);
        } else {
            return "The NPC has nothing to say about that.";
        }
    }

    // Method to display NPC status
    public String getStatus() {
        return getName() + " [Strength: " + getStrength() + ", Agility: " + getAgility() + ", Life Points: " + getLifePoints() + "]";
    }

    @Override
    public String toString() {
        return getName() + " - " + (hostile ? "Hostile" : "Friendly");
    }
}
