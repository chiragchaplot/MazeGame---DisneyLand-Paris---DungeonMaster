package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.PurchaseItemCommand;
import mazegame.entity.Player;
import mazegame.entity.Shop;
import mazegame.entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PurchaseItemCommandTest {

    private PurchaseItemCommand purchaseItemCommand;
    private Player player;
    private Shop shop;

    @BeforeEach
    public void setUp() {
        purchaseItemCommand = new PurchaseItemCommand();
        player = new Player("TestPlayer");
        shop = new Shop("General Store", "Shop");
        player.setCurrentLocation(shop);
    }

    @Test
    public void testPurchaseItemNotInShop() {
        ParsedInput parsedInput = new ParsedInput("purchaseitem", new ArrayList<>(List.of("1")));
        CommandResponse response = purchaseItemCommand.execute(parsedInput, player);

        assertEquals("Item not found in the shop.", response.getMessage());
    }
}
