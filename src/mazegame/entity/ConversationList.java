package mazegame.entity;

import java.util.HashMap;

public class ConversationList {

    private HashMap<String, String> conversations;

    public ConversationList() {
        conversations = new HashMap<>();
    }

    // Add a conversation response to the list
    public void addConversation(String input, String response) {
        conversations.put(input, response);
    }

    // Retrieve the response based on player input
    public String getResponse(String input) {
        return conversations.getOrDefault(input, "The character has no response for that.");
    }

    // Method to get all conversations (for debugging or other purposes)
    public HashMap<String, String> getAllConversations() {
        return conversations;
    }
}
