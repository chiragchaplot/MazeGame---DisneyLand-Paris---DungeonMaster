package mazegame;

import java.util.ArrayList;

import mazegame.boundary.IMazeData;
import mazegame.entity.*;

public class HardCodedData implements IMazeData {
	private Location startUp;
	private Location sleepingBeautyCastle;
    private Location adventureland;
    private Location discoveryland;
    private Location frontierland;
    private Location fantasyland;
    private Location piratesOfTheCaribbean;
    private Location phantomManor;
    private Location bigThunderMountain;
    
	private ArrayList<Location> map = new ArrayList<Location>();
	
	public HardCodedData()
	{
		createWorldMap();
	}
	
	public Location getStartingLocation()
	{
		return startUp;
	}
	
	public String getWelcomeMessage()
	{
		return "Welcome to the Mount Helanous";
	}
	
	private void createWorldMap() 
	{
	    createLocations();
	    createShops();
	    createNPCs();
	    connectLocations();
//	    populateItemsInLocations();
	}

	private void createLocations()
	{
	    // Create Disneyland Paris-themed Locations
	    startUp = new Location("The grand entrance to Disneyland Paris, where your magical journey begins", "Disneyland Entrance");
	    sleepingBeautyCastle = new Location("The iconic Sleeping Beauty Castle, the heart of Disneyland Paris", "Sleeping Beauty Castle");
	    adventureland = new Location("An exotic and adventurous land filled with surprises", "Adventureland");
	    discoveryland = new Location("A futuristic world of innovation and exploration", "Discoveryland");
	    frontierland = new Location("A rugged, wild west town filled with adventure", "Frontierland");
	    fantasyland = new Location("A whimsical, enchanting land where fairy tales come to life", "Fantasyland");
	    piratesOfTheCaribbean = new Location("A thrilling pirate adventure on the high seas", "Pirates of the Caribbean");
	    phantomManor = new Location("A spooky, mysterious haunted house experience", "Phantom Manor");
	    bigThunderMountain = new Location("A thrilling runaway mine train ride through the wild west", "Big Thunder Mountain");

	    // Add locations to the map
	    map.add(startUp);
	    map.add(sleepingBeautyCastle);
	    map.add(adventureland);
	    map.add(discoveryland);
	    map.add(frontierland);
	    map.add(fantasyland);
	    map.add(piratesOfTheCaribbean);
	    map.add(phantomManor);
	    map.add(bigThunderMountain);
	}

	private void createShops() 
	{
	    // Create Disneyland Paris-themed Shops
	    Shop emporium = new Shop("A classic shop on Main Street USA offering Disney-themed merchandise", "Emporium");
	    Shop thunderMesaMercantileBuilding = new Shop("A western-themed shop offering souvenirs and clothing", "Thunder Mesa Mercantile Building");
	    Shop laBoutiquest = new Shop("A boutique featuring exclusive Disney jewelry and accessories", "La Boutique du Château");
	    Shop theLodge = new Shop("A cozy shop in Frontierland with wilderness-themed gifts", "The Lodge");

	    // Add shops to the map
	    map.add(emporium);
	    map.add(thunderMesaMercantileBuilding);
	    map.add(laBoutiquest);
	    map.add(theLodge);
	}

	private void createNPCs()
	{
	    // Create Non-Player Characters (NPC)
	    RandomNumberGenerator gen = new RandomNumberGenerator();
	    NonPlayableCharacter mickeyMouse = new NonPlayableCharacter("Mickey Mouse");
//	    mickeyMouse.setStrength(StrengthTable.getInstance().getStrength(gen.generateRandomInRange(1, 10)));
//	    mickeyMouse.setAgility(AgilityTable.getInstance().getAgility(gen.generateRandomInRange(1, 10)));
	    mickeyMouse.setLifePoints(20);
	    mickeyMouse.setHostile(false);

	    NonPlayableCharacter maleficent = new NonPlayableCharacter("Maleficent");
//	    maleficent.setStrength(StrengthTable.getInstance().getStrength(gen.generateRandomInRange(1, 10)));
//	    maleficent.setAgility(AgilityTable.getInstance().getAgility(gen.generateRandomInRange(1, 10)));
	    maleficent.setLifePoints(25);
	    maleficent.setHostile(true);

	    NonPlayableCharacter goofy = new NonPlayableCharacter("Goofy");
	    goofy.setHostile(false);
//	    goofy.setConversationListMap(conversationList);
	}

	private void connectLocations()
	{
	    // Connect Locations (Disneyland Paris Attractions)
	    startUp.addExit("north", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));
	    startUp.addExit("west", new Exit("Adventureland", adventureland));
	    startUp.addExit("east", new Exit("Discoveryland", discoveryland));
	    startUp.addExit("southwest", new Exit("Frontierland", frontierland));
	    startUp.addExit("southeast", new Exit("Fantasyland", fantasyland));

	    sleepingBeautyCastle.addExit("south", new Exit("Disneyland Entrance", startUp));
	    sleepingBeautyCastle.addExit("northwest", new Exit("Pirates of the Caribbean", piratesOfTheCaribbean));
	    sleepingBeautyCastle.addExit("northeast", new Exit("Phantom Manor", phantomManor));

	    adventureland.addExit("east", new Exit("Disneyland Entrance", startUp));
	    adventureland.addExit("north", new Exit("Big Thunder Mountain", bigThunderMountain));

	    discoveryland.addExit("west", new Exit("Disneyland Entrance", startUp));

	    frontierland.addExit("northeast", new Exit("Disneyland Entrance", startUp));

	    fantasyland.addExit("northwest", new Exit("Disneyland Entrance", startUp));

	    piratesOfTheCaribbean.addExit("south", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));

	    phantomManor.addExit("south", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));

	    bigThunderMountain.addExit("south", new Exit("Adventureland", adventureland));
	}

	private void populateItemsInLocations()
	{
	    // Adding Items to Locations
//	    adventureland.createItems("items", itemsList2); // Adding some adventure-themed items in Adventureland
//	    sleepingBeautyCastle.createItems("items", itemsList4); // Adding treasures in Sleeping Beauty Castle
//	    frontierland.createItems("weapon", weaponList); // Adding weapons in Frontierland
//	    emporium.createItems("items", itemsList5); // Adding merchandise in Emporium
//	    thunderMesaMercantileBuilding.createItems("items", itemsList6); // Adding souvenirs in Thunder Mesa Mercantile Building
	}

}
