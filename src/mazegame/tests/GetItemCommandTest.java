package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.GetItemCommand;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class GetItemCommandTest {

    private GetItemCommand getItemCommand;
    private Player player;
    private Location location;

    @BeforeEach
    public void setUp() {
        getItemCommand = new GetItemCommand();
        player = new Player("TestPlayer");
        location = new Location("A quiet forest clearing", "Forest");
        player.setCurrentLocation(location);
    }

    @Test
    public void testGetItemWithoutItemId() {
        ParsedInput parsedInput = new ParsedInput("getitem", new ArrayList<>());
        CommandResponse response = getItemCommand.execute(parsedInput, player);

        assertEquals("Please specify the item ID you want to collect.", response.getMessage());
    }

    @Test
    public void testGetItemWithInvalidIdFormat() {
        ParsedInput parsedInput = new ParsedInput("getitem", new ArrayList<>(List.of("invalid")));
        CommandResponse response = getItemCommand.execute(parsedInput, player);

        assertEquals("Invalid item ID. Please enter a valid number.", response.getMessage());
    }

    @Test
    public void testGetItemNotInLocation() {
        ParsedInput parsedInput = new ParsedInput("getitem", new ArrayList<>(List.of("1")));
        CommandResponse response = getItemCommand.execute(parsedInput, player);

        assertEquals("No item with ID '1' found at this location.", response.getMessage());
    }

    @Test
    public void testSuccessfulGetItem() {
        Item sword = new Item("Sword", "A sharp blade", 1.0, 100.0);
        location.addItem(sword);

        ParsedInput parsedInput = new ParsedInput("getitem", new ArrayList<>(List.of(String.valueOf(sword.getId()))));
        CommandResponse response = getItemCommand.execute(parsedInput, player);

        assertEquals("You have successfully collected the item: Sword", response.getMessage());
        assertTrue(player.getInventory().getItems().contains(sword), "Item should be in the player's inventory.");
        assertTrue(!location.getItems().contains(sword), "Item should no longer be in the location.");
    }
}
