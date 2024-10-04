package mazegame;

import java.util.ArrayList;

import mazegame.boundary.IMazeData;
import mazegame.entity.*;
import mazegame.entity.items.*;

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
    
    private Shop emporium;
    private Shop thunderMesaMercantileBuilding;
    private Shop laBoutiquest;
    private Shop theLodge;
    
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
	    connectShops();
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
	    emporium = new Shop("A classic shop on Main Street USA offering Disney-themed merchandise", "Emporium");
	    thunderMesaMercantileBuilding = new Shop("A western-themed shop offering souvenirs and clothing", "Thunder Mesa Mercantile Building");
	    laBoutiquest = new Shop("A boutique featuring exclusive Disney jewelry and accessories", "La Boutique du Château");
	    theLodge = new Shop("A cozy shop in Frontierland with wilderness-themed gifts", "The Lodge");

	    // Add shops to the map
	    map.add(emporium);
	    map.add(thunderMesaMercantileBuilding);
	    map.add(laBoutiquest);
	    map.add(theLodge);
	    
	    populateShopWithItems(emporium);
        populateShopWithItems(thunderMesaMercantileBuilding);
        populateShopWithItems(laBoutiquest);
        populateShopWithItems(theLodge);
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
	    mickeyMouse.setConversationListMap(createNonHostileConversation1().getAllConversations());

	    NonPlayableCharacter maleficent = new NonPlayableCharacter("Maleficent");
//	    maleficent.setStrength(StrengthTable.getInstance().getStrength(gen.generateRandomInRange(1, 10)));
//	    maleficent.setAgility(AgilityTable.getInstance().getAgility(gen.generateRandomInRange(1, 10)));
	    maleficent.setLifePoints(25);
	    maleficent.setHostile(true);
	    maleficent.setConversationListMap(createHostileConversation().getAllConversations());

	    NonPlayableCharacter goofy = new NonPlayableCharacter("Goofy");
	    goofy.setHostile(false);
	    goofy.setConversationListMap(createNonHostileConversation2().getAllConversations());

	}

	private void connectLocations() {
	    // Connect Locations (Disneyland Paris Attractions)

	    // Connecting startUp to other locations
	    startUp.addExit("north", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));
	    sleepingBeautyCastle.addExit("south", new Exit("Disneyland Entrance", startUp)); // Reverse connection

	    startUp.addExit("west", new Exit("Adventureland", adventureland));
	    adventureland.addExit("east", new Exit("Disneyland Entrance", startUp)); // Reverse connection

	    startUp.addExit("east", new Exit("Discoveryland", discoveryland));
	    discoveryland.addExit("west", new Exit("Disneyland Entrance", startUp)); // Reverse connection

	    startUp.addExit("southwest", new Exit("Frontierland", frontierland));
	    frontierland.addExit("northeast", new Exit("Disneyland Entrance", startUp)); // Reverse connection

	    startUp.addExit("southeast", new Exit("Fantasyland", fantasyland));
	    fantasyland.addExit("northwest", new Exit("Disneyland Entrance", startUp)); // Reverse connection

	    // Connecting Sleeping Beauty Castle to other locations
	    sleepingBeautyCastle.addExit("northwest", new Exit("Pirates of the Caribbean", piratesOfTheCaribbean));
	    piratesOfTheCaribbean.addExit("southeast", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle)); // Reverse connection

	    sleepingBeautyCastle.addExit("northeast", new Exit("Phantom Manor", phantomManor));
	    phantomManor.addExit("southwest", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle)); // Reverse connection

	    // Connecting Adventureland to other locations
	    adventureland.addExit("north", new Exit("Big Thunder Mountain", bigThunderMountain));
	    bigThunderMountain.addExit("south", new Exit("Adventureland", adventureland)); // Reverse connection
	}

	private void connectShops() {
	    // Connect Emporium and Thunder Mesa Mercantile Building to startUp (Disneyland Entrance)
	    startUp.addExit("northwest", new Exit("Emporium", emporium));
	    emporium.addExit("southeast", new Exit("Disneyland Entrance", startUp));  // Reverse connection

	    startUp.addExit("northeast", new Exit("Thunder Mesa Mercantile Building", thunderMesaMercantileBuilding));
	    thunderMesaMercantileBuilding.addExit("southwest", new Exit("Disneyland Entrance", startUp));  // Reverse connection

	    // Connect Emporium to Big Thunder Mountain
	    emporium.addExit("north", new Exit("Big Thunder Mountain", bigThunderMountain));
	    bigThunderMountain.addExit("south", new Exit("Emporium", emporium));  // Reverse connection

	    // Connect Thunder Mesa Mercantile Building to Pirates of the Caribbean
	    thunderMesaMercantileBuilding.addExit("northwest", new Exit("Pirates of the Caribbean", piratesOfTheCaribbean));
	    piratesOfTheCaribbean.addExit("southeast", new Exit("Thunder Mesa Mercantile Building", thunderMesaMercantileBuilding));  // Reverse connection

	    // Connect La Boutique du Château to Sleeping Beauty Castle
	    laBoutiquest.addExit("south", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));
	    sleepingBeautyCastle.addExit("north", new Exit("La Boutique du Château", laBoutiquest));  // Reverse connection

	    // Connect The Lodge to Phantom Manor
	    theLodge.addExit("west", new Exit("Phantom Manor", phantomManor));
	    phantomManor.addExit("east", new Exit("The Lodge", theLodge));  // Reverse connection
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
	
	private ConversationList createNonHostileConversation1() {
	    ConversationList conversationList = new ConversationList();
	    conversationList.addConversation("hello", "Hi there! Welcome to Disneyland Paris. How can I help you today?");
	    conversationList.addConversation("good", "Glad to hear you're enjoying your time at Disneyland! Did you see Sleeping Beauty Castle yet?");
	    conversationList.addConversation("castle", "The Sleeping Beauty Castle is just to the north. It's magical!");
	    conversationList.addConversation("adventureland", "Head west to Adventureland for some thrilling rides! Watch out for the pirates!");
	    conversationList.addConversation("bye", "See you around, pal! Have a magical day!");
	    return conversationList;
	}


	private ConversationList createNonHostileConversation2() {
	    ConversationList conversationList = new ConversationList();
	    conversationList.addConversation("hello", "Gawrsh! Howdy, pal! What brings you to Disneyland?");
	    conversationList.addConversation("adventureland", "Oh boy! Adventureland is my favorite! Just head west from here.");
	    conversationList.addConversation("phantom", "Phantom Manor? That's one spooky place! It's northeast from Sleeping Beauty Castle.");
	    conversationList.addConversation("bigthunder", "Big Thunder Mountain? Yeehaw! You’ll find it north of Adventureland.");
	    conversationList.addConversation("bye", "Take care, and have a great time at Disneyland!");
	    return conversationList;
	}


	private ConversationList createHostileConversation() {
	    ConversationList conversationList = new ConversationList();
	    conversationList.addConversation("hello", "Why have you disturbed me? I do not take kindly to trespassers.");
	    conversationList.addConversation("fight", "You dare challenge me? Prepare for your demise!");
	    conversationList.addConversation("flee", "Run while you can, but you will never escape my wrath.");
	    conversationList.addConversation("defeated", "Pathetic fool! You were no match for my power.");
	    return conversationList;
	}

	private void populateShopWithItems(Shop shop) {
        // Get lists of weapons, armors, shields, and potions
        ArrayList<Weapon> weapons = Weapon.createWeaponList();
        ArrayList<Armor> armors = Armor.createArmorList();
        ArrayList<Shield> shields = Shield.createShieldList();
        ArrayList<Potion> potions = Potion.createPotionList();

        for (Weapon weapon : weapons) {
            shop.addItemForSale(weapon);
        }

        for (Armor armor : armors) {
            shop.addItemForSale(armor);
        }

        for (Shield shield : shields) {
            shop.addItemForSale(shield);
        }

        for (Potion potion : potions) {
            shop.addItemForSale(potion);
        }
    }
}
