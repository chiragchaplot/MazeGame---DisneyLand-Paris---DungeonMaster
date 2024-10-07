package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.Location;

import java.util.Scanner;

public class ConverseCommand implements Command {
    private Scanner scanner = new Scanner(System.in);

    public CommandResponse execute(ParsedInput userInput, Player player) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the NPC name to converse with.");
        }

        String npcName = (String) userInput.getArguments().get(0);
        NonPlayableCharacter npc = findNPCByName(player.getCurrentLocation(), npcName);

        if (npc == null) {
            return new CommandResponse("No NPC found with the name: " + npcName);
        }

        if (npc.isHostile()) {
            return new CommandResponse(npc.getName() + " is hostile. You cannot converse with them.");
        }

        // Start conversation with a greeting
        StringBuilder conversation = new StringBuilder();
        conversation.append("You are now conversing with ").append(npc.getName()).append(".\n");
        conversation.append(npc.talk("hello")).append("\n");  // Default greeting from NPC

        System.out.println(conversation.toString());

        // Enter interactive conversation loop
        while (true) {
            System.out.print("Your response (type 'bye' to end conversation): ");
            String playerResponse = scanner.nextLine().trim().toLowerCase();

            // Exit conversation on "bye"
            if (playerResponse.equals("bye")) {
                System.out.println("You ended the conversation with " + npc.getName() + ".");
                break;
            }

            // Get NPC response based on player input
            String npcResponse = npc.talk(playerResponse);
            System.out.println(npc.getName() + ": " + npcResponse);
        }

        return new CommandResponse("Conversation ended with " + npc.getName() + ".");
    }

    private NonPlayableCharacter findNPCByName(Location location, String name) {
        for (NonPlayableCharacter npc : location.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
