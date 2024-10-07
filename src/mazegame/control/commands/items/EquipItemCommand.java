package mazegame.control.commands.items;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;
import mazegame.entity.items.Armor;

public class EquipItemCommand implements Command {

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the item ID to equip.");
        }

        int itemId;
        try {
            itemId = Integer.parseInt(String.valueOf(userInput.getArguments().get(0)));
        } catch (NumberFormatException e) {
            return new CommandResponse("Invalid item ID. Please enter a valid number.");
        }

        Item itemToEquip = thePlayer.getInventory().getItemById(itemId);

        if (itemToEquip == null) {
            return new CommandResponse("You do not have an item with that ID.");
        }

        if (itemToEquip instanceof Weapon) {
            thePlayer.equipWeapon((Weapon) itemToEquip);
            return new CommandResponse("You have equipped the weapon: " + itemToEquip.getLabel());
        } else if (itemToEquip instanceof Armor) {
            thePlayer.equipArmor((Armor) itemToEquip);
            return new CommandResponse("You have equipped the armor: " + itemToEquip.getLabel());
        } else {
            return new CommandResponse("This item cannot be equipped.");
        }
    }
}
