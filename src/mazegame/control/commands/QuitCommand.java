package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class QuitCommand implements Command {

	public CommandResponse execute (ParsedInput input, Player thePlayer) {
		return new CommandResponse ("Thanks for playing --- Goodbye", true);
	}
}
