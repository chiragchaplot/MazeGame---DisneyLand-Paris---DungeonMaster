package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Location;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.items.Item;

public class ListItemsFromNPCCommand implements Command {

    @Override
    public CommandResponse execute(ParsedInput userInput, Player player) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the NPC name to list their items.");
        }

        String npcName = userInput.getArguments().get(0).toString();
        NonPlayableCharacter npc = findNPCInLocation(player.getCurrentLocation(), npcName);

        if (npc == null) {
            return new CommandResponse("No NPC found with the name: " + npcName + " at this location.");
        }

        if (npc.getItems().isEmpty()) {
            return new CommandResponse(npc.getName() + " is not holding any items.");
        }

        StringBuilder response = new StringBuilder(npc.getName() + " holds the following items:\n");
        for (Item item : npc.getItems()) {
            response.append("ID: ").append(item.getId())
                    .append(", Name: ").append(item.getLabel())
                    .append(", Description: ").append(item.getDescription())
                    .append("\n");
        }

        return new CommandResponse(response.toString());
    }

    private NonPlayableCharacter findNPCInLocation(Location location, String name) {
        for (NonPlayableCharacter npc : location.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
