package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Location;
import mazegame.entity.Shop;

public class DropItemCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID to drop.");
        }

        int itemId;
        try {
            itemId = Integer.parseInt(userInput.getArguments().get(0).toString());
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }

        Location currentLocation = thePlayer.getCurrentLocation();

        if (currentLocation instanceof Shop) {
            return new CommandResponse("You cannot drop items in a shop.");
        }

        Item itemToDrop = thePlayer.getInventory().getItemById(itemId);
        if (itemToDrop == null) {
            return new CommandResponse("You do not have an item with that ID.");
        }

        thePlayer.removeItemFromInventory(itemToDrop);
        currentLocation.addItem(itemToDrop);

        return new CommandResponse("You have dropped the item: " + itemToDrop.getLabel() + " at this location.");
//    	return new CommandResponse("DONE");
    }
}
