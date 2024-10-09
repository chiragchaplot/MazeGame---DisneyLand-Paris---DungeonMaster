package mazegame.tests;

import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.control.commands.items.GetItemFromNPCCommand;
import mazegame.entity.Player;
import mazegame.entity.Party;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetItemFromNPCCommandTest {

    private GetItemFromNPCCommand getItemFromNPCCommand;
    private Player player;
    private Party party;
    private NonPlayableCharacter friendlyNPC;
    private NonPlayableCharacter hostileNPC;

    @BeforeEach
    public void setUp() {
        party = new Party();
        getItemFromNPCCommand = new GetItemFromNPCCommand(party);
        player = new Player("TestPlayer");

        friendlyNPC = new NonPlayableCharacter("FriendlyNPC");
        friendlyNPC.setHostile(false);
        Item sword = new Item("Sword", "A sharp blade", 1.0, 100.0);
        friendlyNPC.addItem(sword);

        hostileNPC = new NonPlayableCharacter("HostileNPC");
        hostileNPC.setHostile(true);
    }

    @Test
    public void testGetItemFromNPCWithoutArguments() {
        ParsedInput parsedInput = new ParsedInput("getitemfromnpc", new ArrayList<>());
        CommandResponse response = getItemFromNPCCommand.execute(parsedInput, player);

        assertEquals("Please specify the NPC name and item ID to take the item.", response.getMessage());
    }

    @Test
    public void testGetItemFromNPCWithInvalidItemIdFormat() {
        party.addMember(friendlyNPC); // Add NPC to party to allow interaction
        ParsedInput parsedInput = new ParsedInput("getitemfromnpc", new ArrayList<>(List.of("FriendlyNPC", "invalid")));
        CommandResponse response = getItemFromNPCCommand.execute(parsedInput, player);

        assertEquals("Invalid item ID. Please enter a numeric ID.", response.getMessage());
    }

    @Test
    public void testGetItemFromNPCNotInParty() {
        ParsedInput parsedInput = new ParsedInput("getitemfromnpc", new ArrayList<>(List.of("HostileNPC", "1")));
        CommandResponse response = getItemFromNPCCommand.execute(parsedInput, player);

        assertEquals("The NPC HostileNPC is not part of your party.", response.getMessage());
    }

    @Test
    public void testGetItemFromNPCWithoutItem() {
        party.addMember(friendlyNPC);
        ParsedInput parsedInput = new ParsedInput("getitemfromnpc", new ArrayList<>(List.of("FriendlyNPC", "99")));
        CommandResponse response = getItemFromNPCCommand.execute(parsedInput, player);

        assertEquals("FriendlyNPC does not have an item with ID: 99", response.getMessage());
    }
}
