package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;

import java.util.ArrayList;

public class GetItemCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID you want to collect.");
        }

        try {
            int itemId = Integer.parseInt((String) userInput.getArguments().get(0));
            ArrayList<Item> itemsInLocation = thePlayer.getCurrentLocation().getItems();

            Item itemToGet = null;
            for (Item item : itemsInLocation) {
                if (item.getId() == itemId) {
                    itemToGet = item;
                    break;
                }
            }

            if (itemToGet == null) {
                return new CommandResponse("No item with ID '" + itemId + "' found at this location.");
            }

            thePlayer.addItemToInventory(itemToGet);
            thePlayer.getCurrentLocation().removeItem(itemToGet);

            return new CommandResponse("You have successfully collected the item: " + itemToGet.getLabel());
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }
    }
}
