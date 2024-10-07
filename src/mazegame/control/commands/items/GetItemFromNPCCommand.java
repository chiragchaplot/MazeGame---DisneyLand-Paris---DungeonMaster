package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.items.*;
import mazegame.entity.*;

public class GetItemFromNPCCommand implements Command {
    private Party party;

    public GetItemFromNPCCommand(Party party) {
        this.party = party;
    }

    @Override
    public CommandResponse execute(ParsedInput userInput, Player player) {
        if (userInput.getArguments().size() < 2) {
            return new CommandResponse("Please specify the NPC name and item ID to take the item.");
        }

        String npcName = userInput.getArguments().get(0).toString();
        String itemIdStr = userInput.getArguments().get(1).toString();

        int itemId;
        try {
            itemId = Integer.parseInt(itemIdStr);
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a numeric ID.");
        }

        NonPlayableCharacter npc = findNPCInParty(npcName);

        if (npc == null) {
            return new CommandResponse("The NPC " + npcName + " is not part of your party.");
        }

        Item item = npc.getItemById(itemId);
        if (item == null) {
            return new CommandResponse(npc.getName() + " does not have an item with ID: " + itemId);
        }

        player.addItemToInventory(item);
        npc.removeItem(item);
        
        return new CommandResponse("You have successfully taken the item: " + item.getLabel() + " from " + npc.getName());
    }

    private NonPlayableCharacter findNPCInParty(String name) {
        for (NonPlayableCharacter npc : party.getPartyMembers()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
