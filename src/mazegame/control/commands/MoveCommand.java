package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Exit;
import mazegame.entity.Location;
import mazegame.entity.Party;
import mazegame.entity.Player;

public class MoveCommand implements Command {
    private Party party;

    public MoveCommand(Party party) {
        this.party = party;
    }

    @Override
    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("If you want to move, you need to tell me where.");
        }

        String exitLabel = (String) userInput.getArguments().get(0);
        Exit desiredExit = thePlayer.getCurrentLocation().getExit(exitLabel);

        if (desiredExit == null) {
            return new CommandResponse("There is no exit there... try moving somewhere else!");
        }

        Location currentLocation = thePlayer.getCurrentLocation();
        Location newLocation = desiredExit.getDestination();

        thePlayer.setCurrentLocation(newLocation);
        party.moveParty(currentLocation, newLocation);

        StringBuilder response = new StringBuilder();
        response.append("You successfully move ").append(exitLabel).append(" and find yourself at:\n\n");
        response.append(newLocation.toString());

        if (!party.getPartyMembers().isEmpty()) {
            response.append("\nYour party members have moved with you:\n");
            for (var npc : party.getPartyMembers()) {
                response.append("- ").append(npc.getName()).append("\n");
            }
        }

        return new CommandResponse(response.toString());
    }
}
