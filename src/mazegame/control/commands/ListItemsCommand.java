package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;

import java.util.ArrayList;

public class ListItemsCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (thePlayer.getInventory() == null || thePlayer.getInventory().getItems().isEmpty()) {
            return new CommandResponse("You are not holding any items.");
        }

        ArrayList<Item> playerItems = thePlayer.getInventory().getItems();
        
        StringBuilder itemList = new StringBuilder("You are holding the following items:\n");
        for (Item item : playerItems) {
            itemList.append(String.format("[%d] %s - %s\n", 
                            item.getId(), item.getLabel(), item.getDescription()));
        }
        
        return new CommandResponse(itemList.toString());
    }
}
