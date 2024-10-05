package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Shop;

public class PurchaseItemCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID to purchase.");
        }

        int itemId;
        try {
            itemId = Integer.parseInt((String) userInput.getArguments().get(0)); // Cast to String
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }

        if (!(thePlayer.getCurrentLocation() instanceof Shop)) {
            return new CommandResponse("You can only purchase items in a shop.");
        }

        Shop currentShop = (Shop) thePlayer.getCurrentLocation();
        Item itemToPurchase = currentShop.getItemsForSale().stream()
                                          .filter(item -> item.getId() == itemId)
                                          .findFirst()
                                          .orElse(null);

        if (itemToPurchase == null) {
            return new CommandResponse("Item not found in the shop.");
        }

        if (thePlayer.getWeight() < itemToPurchase.getWeight()) {
            return new CommandResponse("You cannot carry more items. Reduce your inventory weight.");
        }

        thePlayer.addItemToInventory(itemToPurchase);
        currentShop.removeItemFromSale(itemToPurchase);

        return new CommandResponse("You have purchased: " + itemToPurchase.getLabel());
    }
}
