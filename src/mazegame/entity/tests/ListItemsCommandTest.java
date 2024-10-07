package mazegame.entity.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.ListItemsCommand;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListItemsCommandTest {

    private ListItemsCommand listItemsCommand;
    private Player player;

    @BeforeEach
    public void setUp() {
        listItemsCommand = new ListItemsCommand();
        player = new Player("TestPlayer");
    }

    @Test
    public void testListItemsWhenInventoryIsEmpty() {
        CommandResponse response = listItemsCommand.execute(new ParsedInput("listitems", new ArrayList<>()), player);
        assertEquals("You are not holding any items.", response.getMessage());
    }

    @Test
    public void testListItemsWithItemsInInventory() {
        Item sword = new Item("Sword", "A sharp blade", 1.0, 100.0);
        player.addItemToInventory(sword);

        CommandResponse response = listItemsCommand.execute(new ParsedInput("listitems", new ArrayList<>()), player);
        assertEquals("You are holding the following items:\n[1] Sword - A sharp blade\n", response.getMessage());
    }
}
