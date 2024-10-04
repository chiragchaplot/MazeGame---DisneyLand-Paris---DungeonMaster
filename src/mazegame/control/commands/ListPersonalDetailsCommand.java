package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class ListPersonalDetailsCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        StringBuilder details = new StringBuilder("Player Details:\n");
        details.append("Name: ").append(thePlayer.getName()).append("\n");
        details.append("Agility: ").append(thePlayer.getAgility()).append("\n");
        details.append("Life Points: ").append(thePlayer.getLifePoints()).append("\n");
        details.append("Strength: ").append(thePlayer.getStrength()).append("\n");
        details.append("Weight: ").append(thePlayer.getWeight()).append("\n");
        details.append("Current Location: ").append(thePlayer.getCurrentLocation().getLabel()).append("\n");
        details.append("Inventory: ").append(thePlayer.getInventory().getItems().size()).append(" item(s)\n");
        return new CommandResponse(details.toString());
    }
}
