package mazegame.entity.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.DropItemCommand;
import mazegame.entity.Player;
import mazegame.entity.items.Item;
import mazegame.entity.Location;
import mazegame.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropItemCommandTest {

    private DropItemCommand dropItemCommand;
    private Player player;
    private Location location;

    @BeforeEach
    public void setUp() {
        dropItemCommand = new DropItemCommand();
        player = new Player("TestPlayer");
        location = new Location("A quiet place", "Forest");
        player.setCurrentLocation(location);
    }

    @Test
    public void testDropItemWithoutItemId() {
        ParsedInput parsedInput = new ParsedInput("dropitem", new ArrayList<>());
        CommandResponse response = dropItemCommand.execute(parsedInput, player);

        assertEquals("Please specify the item ID to drop.", response.getMessage());
    }

    @Test
    public void testDropItemWithInvalidIdFormat() {
        ParsedInput parsedInput = new ParsedInput("dropitem", new ArrayList<>(List.of("invalid")));
        CommandResponse response = dropItemCommand.execute(parsedInput, player);
        
        assertEquals("Invalid item ID. Please enter a valid number.", response.getMessage());
    }

    @Test
    public void testDropItemNotInInventory() {
        ParsedInput parsedInput = new ParsedInput("dropitem", new ArrayList<>(List.of("99")));
        CommandResponse response = dropItemCommand.execute(parsedInput, player);
        System.out.println(response.getMessage());
        assertEquals("You do not have an item with that ID.", response.getMessage());
    }
}
