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
        availableCommands = new HashMap<String, Command>();
        commands = new ArrayList<String>();
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
        availableCommands.put("help", new HelpCommand(availableCommands.keySet()));
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
