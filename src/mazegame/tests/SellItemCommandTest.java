package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.SellItemCommand;
import mazegame.entity.Player;
import mazegame.entity.Shop;
import mazegame.entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class SellItemCommandTest {

    private SellItemCommand sellItemCommand;
    private Player player;
    private Shop shop;

    @BeforeEach
    public void setUp() {
        sellItemCommand = new SellItemCommand();
        player = new Player("TestPlayer");
        shop = new Shop("General Store", "Shop");
        player.setCurrentLocation(shop);
    }

    @Test
    public void testSellItemNotInInventory() {
        ParsedInput parsedInput = new ParsedInput("sellitem", new ArrayList<>(List.of("99")));
        CommandResponse response = sellItemCommand.execute(parsedInput, player);
        assertEquals("You do not have an item with that ID.", response.getMessage());
    }
}
