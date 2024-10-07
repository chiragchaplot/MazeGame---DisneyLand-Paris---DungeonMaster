package mazegame.entity;

import java.util.HashMap;

public class ConversationList {

    private HashMap<String, String> conversations;

    public ConversationList() {
        conversations = new HashMap<>();
    }

    public void addConversation(String input, String response) {
        conversations.put(input, response);
    }

    public String getResponse(String input) {
        return conversations.getOrDefault(input, "The character has no response for that.");
    }

    public HashMap<String, String> getAllConversations() {
        return conversations;
    }
}
