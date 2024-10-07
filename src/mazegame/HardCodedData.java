package mazegame;

import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<Location> map = new ArrayList<>();

    public HardCodedData() {
        createWorldMap();
    }

    public Location getStartingLocation() {
        return startUp;
    }

    public String getWelcomeMessage() {
        return "Welcome to the DisneyLand - Paris";
    }

    public ArrayList<Location> getLocations() {
        return map;
    }

    private void createWorldMap() {
        createLocations();
        createShops();
        connectLocations();
        connectShops();
        populateItemsInLocations();
        createNPCs();
    }

    private void createLocations() {
        startUp = new Location("The grand entrance to Disneyland Paris, where your magical journey begins", "Disneyland Entrance");
        sleepingBeautyCastle = new Location("The iconic Sleeping Beauty Castle, the heart of Disneyland Paris", "Sleeping Beauty Castle");
        adventureland = new Location("An exotic and adventurous land filled with surprises", "Adventureland");
        discoveryland = new Location("A futuristic world of innovation and exploration", "Discoveryland");
        frontierland = new Location("A rugged, wild west town filled with adventure", "Frontierland");
        fantasyland = new Location("A whimsical, enchanting land where fairy tales come to life", "Fantasyland");
        piratesOfTheCaribbean = new Location("A thrilling pirate adventure on the high seas", "Pirates of the Caribbean");
        phantomManor = new Location("A spooky, mysterious haunted house experience", "Phantom Manor");
        bigThunderMountain = new Location("A thrilling runaway mine train ride through the wild west", "Big Thunder Mountain");

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

    private void createShops() {
        emporium = new Shop("A classic shop on Main Street USA offering Disney-themed merchandise", "Emporium");
        thunderMesaMercantileBuilding = new Shop("A western-themed shop offering souvenirs and clothing", "Thunder Mesa Mercantile Building");
        laBoutiquest = new Shop("A boutique featuring exclusive Disney jewelry and accessories", "La Boutique du Château");
        theLodge = new Shop("A cozy shop in Frontierland with wilderness-themed gifts", "The Lodge");

        map.add(emporium);
        map.add(thunderMesaMercantileBuilding);
        map.add(laBoutiquest);
        map.add(theLodge);

        populateShopWithItems(emporium);
        populateShopWithItems(thunderMesaMercantileBuilding);
        populateShopWithItems(laBoutiquest);
        populateShopWithItems(theLodge);
    }

    private void createNPCs() {
        RandomNumberGenerator gen = new RandomNumberGenerator();
        ArrayList<Weapon> weapons = Weapon.createWeaponList();
        Random random = new Random();
        
        NonPlayableCharacter mickeyMouse = new NonPlayableCharacter("MickeyMouse");
        mickeyMouse.setLifePoints(gen.generateRandomInRange(1, 10));
        mickeyMouse.setStrength(gen.generateRandomInRange(1, 10));
        mickeyMouse.setHostile(false);
        mickeyMouse.setConversationListMap(createNonHostileConversation1().getAllConversations());
        mickeyMouse.equipWeapon(weapons.get(random.nextInt(weapons.size())));
        startUp.addNPC(mickeyMouse);

        NonPlayableCharacter maleficent = new NonPlayableCharacter("Maleficent");
        maleficent.setLifePoints(10);
        maleficent.setStrength(gen.generateRandomInRange(1, 10));
        maleficent.setHostile(true);
        maleficent.setConversationListMap(createHostileConversation().getAllConversations());
        maleficent.equipWeapon(weapons.get(random.nextInt(weapons.size())));
        phantomManor.addNPC(maleficent);

        NonPlayableCharacter goofy = new NonPlayableCharacter("Goofy");
        goofy.setLifePoints(gen.generateRandomInRange(1, 10));
        goofy.setStrength(gen.generateRandomInRange(1, 10));
        goofy.setHostile(false);
        goofy.equipWeapon(weapons.get(random.nextInt(weapons.size())));
        goofy.setConversationListMap(createNonHostileConversation2().getAllConversations());
        adventureland.addNPC(goofy);
    }

    private void connectLocations() {
        startUp.addExit("north", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));
        sleepingBeautyCastle.addExit("south", new Exit("Disneyland Entrance", startUp));

        startUp.addExit("west", new Exit("Adventureland", adventureland));
        adventureland.addExit("east", new Exit("Disneyland Entrance", startUp));

        startUp.addExit("east", new Exit("Discoveryland", discoveryland));
        discoveryland.addExit("west", new Exit("Disneyland Entrance", startUp));

        startUp.addExit("southwest", new Exit("Frontierland", frontierland));
        frontierland.addExit("northeast", new Exit("Disneyland Entrance", startUp));

        startUp.addExit("southeast", new Exit("Fantasyland", fantasyland));
        fantasyland.addExit("northwest", new Exit("Disneyland Entrance", startUp));

        sleepingBeautyCastle.addExit("northwest", new Exit("Pirates of the Caribbean", piratesOfTheCaribbean));
        piratesOfTheCaribbean.addExit("southeast", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));

        sleepingBeautyCastle.addExit("northeast", new Exit("Phantom Manor", phantomManor));
        phantomManor.addExit("southwest", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));

        adventureland.addExit("north", new Exit("Big Thunder Mountain", bigThunderMountain));
        bigThunderMountain.addExit("south", new Exit("Adventureland", adventureland));
    }

    private void connectShops() {
        startUp.addExit("northwest", new Exit("Emporium", emporium));
        emporium.addExit("southeast", new Exit("Disneyland Entrance", startUp));

        startUp.addExit("northeast", new Exit("Thunder Mesa Mercantile Building", thunderMesaMercantileBuilding));
        thunderMesaMercantileBuilding.addExit("southwest", new Exit("Disneyland Entrance", startUp));

        emporium.addExit("north", new Exit("Big Thunder Mountain", bigThunderMountain));
        bigThunderMountain.addExit("south", new Exit("Emporium", emporium));

        thunderMesaMercantileBuilding.addExit("northwest", new Exit("Pirates of the Caribbean", piratesOfTheCaribbean));
        piratesOfTheCaribbean.addExit("southeast", new Exit("Thunder Mesa Mercantile Building", thunderMesaMercantileBuilding));

        laBoutiquest.addExit("south", new Exit("Sleeping Beauty Castle", sleepingBeautyCastle));
        sleepingBeautyCastle.addExit("north", new Exit("La Boutique du Château", laBoutiquest));

        theLodge.addExit("west", new Exit("Phantom Manor", phantomManor));
        phantomManor.addExit("east", new Exit("The Lodge", theLodge));
    }

    private void populateItemsInLocations() {
        ArrayList<Weapon> weapons = Weapon.createWeaponList();
        ArrayList<Armor> armors = Armor.createArmorList();
        ArrayList<Shield> shields = Shield.createShieldList();
        ArrayList<Potion> potions = Potion.createPotionList();

        ArrayList<Item> allItems = new ArrayList<>();
        allItems.addAll(weapons);
        allItems.addAll(armors);
        allItems.addAll(shields);
        allItems.addAll(potions);

        Random random = new Random();

        assignRandomItemsToLocation(sleepingBeautyCastle, allItems, random);
        assignRandomItemsToLocation(adventureland, allItems, random);
        assignRandomItemsToLocation(discoveryland, allItems, random);
        assignRandomItemsToLocation(frontierland, allItems, random);
        assignRandomItemsToLocation(fantasyland, allItems, random);
        assignRandomItemsToLocation(piratesOfTheCaribbean, allItems, random);
        assignRandomItemsToLocation(phantomManor, allItems, random);
        assignRandomItemsToLocation(bigThunderMountain, allItems, random);
    }

    private void assignRandomItemsToLocation(Location location, ArrayList<Item> allItems, Random random) {
        int firstItemIndex = random.nextInt(allItems.size());
        int secondItemIndex;
        do {
            secondItemIndex = random.nextInt(allItems.size());
        } while (secondItemIndex == firstItemIndex);

        Item firstItem = allItems.get(firstItemIndex);
        Item secondItem = allItems.get(secondItemIndex);

        location.addItems(firstItem, secondItem);
    }

    private ConversationList createNonHostileConversation1() {
        ConversationList conversationList = new ConversationList();
        conversationList.addConversation("hello", "Hi there! Welcome to Disneyland Paris. How can I help you today?");
        conversationList.addConversation("suggestion", "I suggest starting with Sleeping Beauty Castle, it's a sight you won't forget!");
        conversationList.addConversation("hint", "Looking for an adventure? Head to Adventureland to the west!");
        conversationList.addConversation("castle", "The Sleeping Beauty Castle is just to the north. It's magical!");
        conversationList.addConversation("adventureland", "Head west to Adventureland for some thrilling rides! Watch out for the pirates!");
        conversationList.addConversation("bye", "See you around, pal! Have a magical day!");
        conversationList.addConversation("join", "If you’re looking for more fun, let’s head to Fantasyland together!");
        return conversationList;
    }

    private ConversationList createNonHostileConversation2() {
        ConversationList conversationList = new ConversationList();
        conversationList.addConversation("hello", "Gawrsh! Howdy, pal! What brings you to Disneyland?");
        conversationList.addConversation("suggestion", "Adventureland is one of my favorite spots! Head west for some real thrills.");
        conversationList.addConversation("hint", "If you're up for a spooky experience, check out the Phantom Manor northeast of Sleeping Beauty Castle.");
        conversationList.addConversation("phantom", "Phantom Manor? That's one spooky place! It's northeast from Sleeping Beauty Castle.");
        conversationList.addConversation("bigthunder", "Big Thunder Mountain? Yeehaw! You’ll find it north of Adventureland.");
        conversationList.addConversation("bye", "Take care, and have a great time at Disneyland!");
        conversationList.addConversation("join", "How about joining me on a tour of Frontierland? We can explore together!");
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

    public ArrayList<Location> getMap() {
        return map;
    }
}
