package mazegame.control;

import mazegame.boundary.IMazeClient;
import mazegame.boundary.IMazeData;
import mazegame.entity.Player;
import mazegame.entity.Party;

public class DungeonMaster {
	private IMazeClient gameClient;
	private IMazeData gameData;
	private Player thePlayer;
	private CommandHandler playerTurnHandler;
    private Party party;
	
	 public DungeonMaster(IMazeData gameData, IMazeClient gameClient) {
         this.gameData = gameData;
         this.gameClient = gameClient;
         this.party = new Party();
         playerTurnHandler = new CommandHandler(gameData, party);
     }

     public void printWelcome() {
         gameClient.playerMessage(gameData.getWelcomeMessage());
     }

     public void setupPlayer() {
         String playerName = gameClient.getReply("What name do you choose to be known by?");
         thePlayer = new Player(playerName);
         thePlayer.setCurrentLocation(gameData.getStartingLocation());
         gameClient.playerMessage("Welcome " + playerName + "\n\n");
         gameClient.playerMessage("You find yourself looking at ");
         gameClient.playerMessage(gameData.getStartingLocation().getDescription());
         gameClient.playerMessage("\n\nType 'help' for available commands or 'look' to see your surroundings");
     }

     public void runGame() {
         printWelcome();
         setupPlayer();
         while (handlePlayerTurn()) {
        	 
         }
         gameClient.getReply("\n\n<<Hit enter to exit>>");
     }
     
     private boolean handlePlayerTurn() {
    	 CommandResponse playerResponse = playerTurnHandler.processTurn(gameClient.getCommand(), thePlayer);
    	 gameClient.playerMessage(playerResponse.getMessage());
    	 return !playerResponse.isFinishedGame();
     }  
}