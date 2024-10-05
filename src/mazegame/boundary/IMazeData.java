package mazegame.boundary;

import mazegame.entity.Location;
import java.util.List;

public interface IMazeData {
	Location getStartingLocation();
	String getWelcomeMessage();
	List<Location> getLocations();
}
