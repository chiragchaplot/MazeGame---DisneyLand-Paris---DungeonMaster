package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import java.util.Set;

public class HelpCommand implements Command {

    private Set<String> availableCommands;

    public HelpCommand(Set<String> availableCommands) {
        this.availableCommands = availableCommands;
    }

    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        StringBuilder helpMessage = new StringBuilder("Available commands:\n");

        for (String command : availableCommands) {
            helpMessage.append("- ").append(command).append("\n");
        }

        return new CommandResponse(helpMessage.toString());
    }
}
