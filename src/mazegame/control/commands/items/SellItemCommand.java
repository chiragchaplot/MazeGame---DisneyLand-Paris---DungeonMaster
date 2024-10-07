package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Shop;

public class SellItemCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID to sell.");
        }

        int itemId;
        try {
            itemId = Integer.parseInt((String) userInput.getArguments().get(0));
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }

        Item itemToSell = thePlayer.getItemById(itemId);
        if (itemToSell == null) {
            return new CommandResponse("You do not have an item with that ID.");
        }

        if (!(thePlayer.getCurrentLocation() instanceof Shop)) {
            return new CommandResponse("You can only sell items in a shop.");
        }

        Shop currentShop = (Shop) thePlayer.getCurrentLocation();
        currentShop.addItemForSale(itemToSell);
        thePlayer.removeItemFromInventory(itemToSell);

        return new CommandResponse("You have sold: " + itemToSell.getLabel());
    }
}
