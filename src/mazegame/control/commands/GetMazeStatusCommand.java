package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;
import mazegame.entity.Shop;
import mazegame.entity.Location;
import mazegame.boundary.IMazeData;

public class GetMazeStatusCommand implements Command {
    private IMazeData mazeData;

    public GetMazeStatusCommand(IMazeData mazeData) {
        this.mazeData = mazeData;
    }

    @Override
    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        int numberOfLocations = mazeData.getLocations().size();
        int numberOfShops = 0;

        for (Location location : mazeData.getLocations()) {
            if (location instanceof Shop) {
                numberOfShops++;
            }
        }
        numberOfLocations -= numberOfShops;
        String statusMessage = String.format("Maze Status:\nNumber of Locations: %d\nNumber of Shops: %d", 
                                             numberOfLocations, numberOfShops);
        return new CommandResponse(statusMessage);
    }
}
