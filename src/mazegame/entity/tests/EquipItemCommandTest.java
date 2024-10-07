package mazegame.entity.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.EquipItemCommand;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.items.Weapon;
import mazegame.entity.items.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EquipItemCommandTest {

    private EquipItemCommand equipItemCommand;
    private Player player;

    @BeforeEach
    public void setUp() {
        equipItemCommand = new EquipItemCommand();
        player = new Player("TestPlayer");
    }

    @Test
    public void testEquipItemWithoutItemId() {
        ParsedInput parsedInput = new ParsedInput("equipitem", new ArrayList<>());
        CommandResponse response = equipItemCommand.execute(parsedInput, player);
        assertEquals("Please specify the item ID to equip.", response.getMessage());
    }

    @Test
    public void testEquipItemWithInvalidIdFormat() {
        ParsedInput parsedInput = new ParsedInput("equipitem", new ArrayList<>(List.of("invalid")));
        CommandResponse response = equipItemCommand.execute(parsedInput, player);
        assertEquals("Invalid item ID. Please enter a valid number.", response.getMessage());
    }

    @Test
    public void testEquipItemNotInInventory() {
        ParsedInput parsedInput = new ParsedInput("equipitem", new ArrayList<>(List.of("99")));
        CommandResponse response = equipItemCommand.execute(parsedInput, player);
        assertEquals("You do not have an item with that ID.", response.getMessage());
    }

    @Test
    public void testEquipWeapon() {
        Weapon sword = new Weapon("Sword", "A sharp blade", 1.0, 100.0, 15);
        player.getInventory().addItem(sword);

        ParsedInput parsedInput = new ParsedInput("equipitem", new ArrayList<>(List.of(String.valueOf(sword.getId()))));
        CommandResponse response = equipItemCommand.execute(parsedInput, player);

        assertEquals("You have equipped the weapon: Sword", response.getMessage());
        assertNotNull(player.getEquippedWeapon());
        assertEquals(sword, player.getEquippedWeapon());
    }

    @Test
    public void testSwitchEquippedWeapon() {
        Weapon sword = new Weapon("Sword", "A sharp blade", 1.0, 100.0, 15);
        Weapon axe = new Weapon("Axe", "A heavy axe", 2.0, 80.0, 20);
        player.getInventory().addItem(sword);
        player.getInventory().addItem(axe);

        player.equipWeapon(sword); // Initial weapon

        ParsedInput parsedInput = new ParsedInput("equipitem", new ArrayList<>(List.of(String.valueOf(axe.getId()))));
        CommandResponse response = equipItemCommand.execute(parsedInput, player);

        assertEquals("You have equipped the weapon: Axe", response.getMessage());
        assertEquals(axe, player.getEquippedWeapon());
    }
}
