package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.UnequipItemCommand;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class UnequipItemCommandTest {

    private UnequipItemCommand unequipItemCommand;
    private Player player;

    @BeforeEach
    public void setUp() {
        unequipItemCommand = new UnequipItemCommand();
        player = new Player("TestPlayer");
    }

    @Test
    public void testUnequipItemNotInInventory() {
        ParsedInput parsedInput = new ParsedInput("unequipitem", new ArrayList<>(List.of("1")));
        CommandResponse response = unequipItemCommand.execute(parsedInput, player);

        assertEquals("You do not have an item with that ID.", response.getMessage());
    }

    @Test
    public void testSuccessfulUnequip() {
        Weapon sword = new Weapon("Sword", "A sharp blade", 1.0, 100.0, 15);
        player.getInventory().addItem(sword);
        player.equipWeapon(sword);
        ParsedInput parsedInput = new ParsedInput("unequipitem", new ArrayList<>(List.of(sword.getId())));
        CommandResponse response = unequipItemCommand.execute(parsedInput, player);
        assertEquals("You have unequipped the item: Sword", response.getMessage());
        assertEquals(null, player.getEquippedWeapon());
    }
}
