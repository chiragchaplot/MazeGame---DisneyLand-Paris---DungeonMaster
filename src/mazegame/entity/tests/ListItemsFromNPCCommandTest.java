package mazegame.entity.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.ListItemsFromNPCCommand;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListItemsFromNPCCommandTest {

    private ListItemsFromNPCCommand listItemsFromNPCCommand;
    private Player player;
    private NonPlayableCharacter npc;

    @BeforeEach
    public void setUp() {
        listItemsFromNPCCommand = new ListItemsFromNPCCommand();
        player = new Player("TestPlayer");
        npc = new NonPlayableCharacter("TestNPC");
        player.getCurrentLocation().addNPC(npc);
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

        assertEquals("TestNPC holds the following items:\nID: 1, Name: Potion, Description: Healing potion\n", response.getMessage());
    }
}
