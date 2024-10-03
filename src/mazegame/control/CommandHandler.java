package mazegame.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import mazegame.entity.Player;

public class CommandHandler {
	private HashMap<String, Command> availableCommands;
	private ArrayList<String> commands;
	private Parser theParser;
	
	public CommandHandler () {
		availableCommands = new HashMap<String, Command>();
		commands = new ArrayList<String>();
		setupCommands();
		theParser = new Parser(popArrayList());
	}
	
	private void setupCommands () {
		availableCommands.put("go", new MoveCommand());
		availableCommands.put("quit", new QuitCommand());
		availableCommands.put("move", new MoveCommand());
		availableCommands.put("look",  new LookCommand());
	}
	
	private ArrayList<String> popArrayList () {
		Set<String> set = availableCommands.keySet();
		ArrayList <String> temp = new ArrayList<String> ();
		for (String key : set) {
			temp.add(key);
		}
		return temp;
	}
	
	public CommandResponse processTurn (String userInput, Player thePlayer) {
		ParsedInput validInput = theParser.parse(userInput);
		Command theCommand = (Command) availableCommands.get(validInput.getCommand());
		return theCommand.execute(validInput, thePlayer);
	}
}
