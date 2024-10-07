package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import java.util.Map;
import java.util.Set;

public class HelpCommand implements Command {

    private Set<String> availableCommands;
    private Map<String, String> commandDescriptions;

    public HelpCommand(Set<String> availableCommands, Map<String, String> commandDescriptions) {
        this.availableCommands = availableCommands;
        this.commandDescriptions = commandDescriptions;
    }

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        StringBuilder helpMessage = new StringBuilder("Available commands:\n");

        for (String command : availableCommands) {
            String description = commandDescriptions.get(command);
            helpMessage.append("- ").append(command);
            if (description != null) {
                helpMessage.append(": ").append(description);
            }
            helpMessage.append("\n");
        }

        return new CommandResponse(helpMessage.toString());
    }
}
