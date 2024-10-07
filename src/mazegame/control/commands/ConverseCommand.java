package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.Location;
import mazegame.entity.Party;

import java.util.Scanner;

public class ConverseCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    private Party party = new Party();

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

        StringBuilder conversation = new StringBuilder();
        conversation.append("You are now conversing with ").append(npc.getName()).append(".\n");
        conversation.append(npc.talk("hello")).append("\n");

        System.out.println(conversation.toString());

        while (true) {
            System.out.print("Your response (type 'help' for conversation options, 'bye' or 'leave' to end): ");
            String playerResponse = scanner.nextLine().trim().toLowerCase();

            if (playerResponse.equals("bye") || playerResponse.equals("leave")) {
                String exitMessage = playerResponse.equals("bye") ? 
                    "You ended the conversation with " + npc.getName() + "." : 
                    "You decided to leave the conversation with " + npc.getName() + ".";
                System.out.println(exitMessage);
                break;
            }

            if (playerResponse.equals("help")) {
                System.out.println("Available conversation topics:");
                for (String topic : npc.getConversationList().keySet()) {
                    System.out.println("- " + topic);
                }
                continue;
            }

            if (playerResponse.equals("join") && !npc.isHostile()) {
                party.addMember(npc);
                System.out.println(npc.getName() + " has joined your party!");
                break;
            }

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

    public Party getParty() {
        return party;
    }
}
