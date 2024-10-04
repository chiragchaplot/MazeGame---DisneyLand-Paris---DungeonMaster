package mazegame.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import mazegame.control.commands.*;
import mazegame.entity.Player;

public class CommandHandler {
    private HashMap<String, Command> availableCommands;
    private ArrayList<String> commands;
    private Parser theParser;
    
    public CommandHandler() {
        availableCommands = new HashMap<>();
        commands = new ArrayList<>();
        setupCommands();
        theParser = new Parser(popArrayList());
    }
    
    private void setupCommands() {
        availableCommands.put("go", new MoveCommand());
        availableCommands.put("quit", new QuitCommand());
        availableCommands.put("move", new MoveCommand());
        availableCommands.put("look", new LookCommand());
        availableCommands.put("listitems", new ListItemsCommand());
        availableCommands.put("getitem", new GetItemCommand());
    }
    
    private ArrayList<String> popArrayList() {
        Set<String> set = availableCommands.keySet();
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(set);  // Collect available command keys
        return temp;
    }
    
    public CommandResponse processTurn(String userInput, Player thePlayer) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return new CommandResponse("Please enter a command.");
        }
        
        String normalizedInput = userInput.trim().toLowerCase();
        ParsedInput validInput = theParser.parse(normalizedInput);
        Command theCommand = availableCommands.get(validInput.getCommand());

        if (theCommand == null) {
            return new CommandResponse("Invalid command. Please try again.");
        }
        
        return theCommand.execute(validInput, thePlayer);
    }
}
