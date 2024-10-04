package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;

public class UnequipItemCommand implements Command {

    @Override
    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID to unequip.");
        }

        int itemId;
        try {
            // Convert Object to String and parse to integer
            itemId = Integer.parseInt(String.valueOf(userInput.getArguments().get(0)));
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }

        Item itemToUnequip = thePlayer.getInventory().getItemById(itemId);

        if (itemToUnequip == null) {
            return new CommandResponse("You do not have an item with that ID.");
        }

        if (thePlayer.isEquipped(itemToUnequip)) {
            thePlayer.unequipItem(itemToUnequip);
            return new CommandResponse("You have unequipped the item: " + itemToUnequip.getLabel());
        } else {
            return new CommandResponse("This item is not currently equipped.");
        }
    }
}
