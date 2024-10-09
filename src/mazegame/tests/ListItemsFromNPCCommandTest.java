package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.ListItemsFromNPCCommand;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListItemsFromNPCCommandTest {

    private ListItemsFromNPCCommand listItemsFromNPCCommand;
    private Player player;
    private NonPlayableCharacter npc;
    private Location location;

    @BeforeEach
    public void setUp() {
        listItemsFromNPCCommand = new ListItemsFromNPCCommand();
        player = new Player("TestPlayer");
        location = new Location("Test Location", "Testing Grounds");
        player.setCurrentLocation(location);
        npc = new NonPlayableCharacter("TestNPC");
        location.addNPC(npc);
    }

    @Test
    public void testListItemsWhenNPCNotHoldingItems() {
        ParsedInput parsedInput = new ParsedInput("listitemsfromnpc", new ArrayList<>(List.of("TestNPC")));
        CommandResponse response = listItemsFromNPCCommand.execute(parsedInput, player);
        
        assertEquals("TestNPC is not holding any items.", response.getMessage());
    }

    @Test
    public void testListItemsWhenNPCHasItems() {
        Item potion = new Item("Potion", "Healing potion", 0.5, 10.0);
        npc.addItem(potion);

        ParsedInput parsedInput = new ParsedInput("listitemsfromnpc", new ArrayList<>(List.of("TestNPC")));
        CommandResponse response = listItemsFromNPCCommand.execute(parsedInput, player);
        String expectedMessage = "TestNPC holds the following items:\nID: 2, Name: Potion, Description: Healing potion\n";
        assertEquals(expectedMessage, response.getMessage());
    }
}
