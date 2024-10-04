package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;

import java.util.ArrayList;

public class ListItemsCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        ArrayList<Item> playerItems = thePlayer.getInventory().getItems();
        
        if (playerItems.isEmpty()) {
            return new CommandResponse("You are not holding any items.");
        }
        
        StringBuilder itemList = new StringBuilder("You are holding the following items:\n");
        for (Item item : playerItems) {
            itemList.append("[").append(item.getId()).append("] ")
                    .append(item.getLabel()).append(" - ")
                    .append(item.getDescription()).append("\n");
        }
        
        return new CommandResponse(itemList.toString());
    }
}