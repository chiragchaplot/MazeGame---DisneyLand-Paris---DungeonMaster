package mazegame.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import mazegame.boundary.IMazeData;
import mazegame.control.commands.*;
import mazegame.control.commands.items.*;
import mazegame.entity.Party;
import mazegame.entity.Player;

public class CommandHandler {
    private HashMap<String, Command> availableCommands;
    private HashMap<String, String> commandDescriptions;
    private ArrayList<String> commands;
    private Parser theParser;
    private IMazeData mazeData;
    private Party party;
    
    public CommandHandler(IMazeData mazeData, Party party) {
    	this.mazeData = mazeData;
        this.party = party;
        availableCommands = new HashMap<String, Command>();
        commandDescriptions = new HashMap<String, String>();
        commands = new ArrayList<String>();
        setupCommands();
        theParser = new Parser(popArrayList());
    }
    
    private void setupCommands() {
        availableCommands.put("go", new MoveCommand(party));
        commandDescriptions.put("go", "Move in a specified direction.");
        
        availableCommands.put("quit", new QuitCommand());
        commandDescriptions.put("quit", "Exit the game.");
        
        availableCommands.put("move", new MoveCommand(party));
        commandDescriptions.put("move", "Move in a specified direction.");

        availableCommands.put("look", new LookCommand());
        commandDescriptions.put("look", "Look around the current location.");

        availableCommands.put("listitems", new ListItemsCommand());
        commandDescriptions.put("listitems", "List all items you are currently holding.");

        availableCommands.put("inventory", new ListItemsCommand());
        commandDescriptions.put("inventory", "List all items you are currently holding.");

        availableCommands.put("getitem", new GetItemCommand());
        commandDescriptions.put("getitem", "Collect a new item from the current location.");

        availableCommands.put("dropitem", new DropItemCommand());
        commandDescriptions.put("dropitem", "Drop an item you are holding.");

        availableCommands.put("equipitem", new EquipItemCommand());
        commandDescriptions.put("equipitem", "Equip an item (e.g., weapon or armor).");

        availableCommands.put("unequipitem", new UnequipItemCommand());
        commandDescriptions.put("unequipitem", "Unequip an item (e.g., weapon or armor).");

        availableCommands.put("listpersonal", new ListPersonalDetailsCommand());
        commandDescriptions.put("listpersonal", "List your personal details.");

        availableCommands.put("self", new ListPersonalDetailsCommand());
        commandDescriptions.put("self", "List your personal details.");

        availableCommands.put("purchaseitem", new PurchaseItemCommand());
        commandDescriptions.put("purchaseitem", "Purchase an item from a vendor/shop.");

        availableCommands.put("sellitem", new SellItemCommand());
        commandDescriptions.put("sellitem", "Sell an item to a vendor/shop.");
        
        availableCommands.put("getmazestatus", new GetMazeStatusCommand(mazeData));
        commandDescriptions.put("getmazestatus", "Display the current maze status (number of locations and shops).");
        
        availableCommands.put("combat", new CombatCommand(party));
        commandDescriptions.put("combat", "Combat Hostile Non Playable Character");
        
        availableCommands.put("converse", new ConverseCommand());
        commandDescriptions.put("converse", "Talk to the NPC");
        
        availableCommands.put("joinparty", new JoinPartyCommand(party));
        commandDescriptions.put("joinparty", "Ask NPC to Join your Quest");

        availableCommands.put("leaveparty", new LeavePartyCommand(party));
        commandDescriptions.put("leaveparty", "Ask NPC to Leave your Quest");
        
        availableCommands.put("listitemsfromnpc", new ListItemsFromNPCCommand());
        commandDescriptions.put("listitemsfromnpc", "List all items held by a specified NPC");
        
        availableCommands.put("getitemfromnpc", new GetItemFromNPCCommand(party));
        commandDescriptions.put("getitemfromnpc", "Get an item from an NPC in your party by specifying NPC name and item ID.");

        availableCommands.put("help", new HelpCommand(availableCommands.keySet(), commandDescriptions));
        commandDescriptions.put("help", "Show the list of available commands and their descriptions.");
    }

    private ArrayList<String> popArrayList() {
        Set<String> set = availableCommands.keySet();
        ArrayList<String> temp = new ArrayList<String>();
        for (String key : set) {
            temp.add(key);
        }
        return temp;
    }

    public CommandResponse processTurn(String userInput, Player thePlayer) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return new CommandResponse("Please enter a command.");
        }
        ParsedInput validInput = theParser.parse(userInput);
        Command theCommand = availableCommands.get(validInput.getCommand());
        if (theCommand == null) {
            return new CommandResponse("Invalid command. Please try again.");
        }
        return theCommand.execute(validInput, thePlayer);
    }
}
