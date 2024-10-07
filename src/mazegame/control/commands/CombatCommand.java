package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.Location;
import mazegame.control.state.CombatState;

public class CombatCommand implements Command {
    public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the NPC name to combat.");
        }

        String npcName = (String) userInput.getArguments().get(0);
        NonPlayableCharacter npc = findNPCByName(thePlayer.getCurrentLocation(), npcName);
        
        if (npc == null) {
            return new CommandResponse("No NPC found with the name: " + npcName);
        }

        if (!npc.isHostile()) {
            return new CommandResponse(npc.getName() + " is friendly. You cannot engage in combat with them.");
        }

        if (!npc.isAlive()) {
            return new CommandResponse(npc.getName() + " has already been defeated. You cannot combat them again.");
        }

        CombatState combatState = new CombatState(thePlayer, npc);
        return startCombat(combatState);
    }

    private NonPlayableCharacter findNPCByName(Location location, String name) {
        for (NonPlayableCharacter npc : location.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }

    private CommandResponse startCombat(CombatState combatState) {
        StringBuilder combatResult = new StringBuilder("Combat started with " + combatState.getNpc().getName() + "!\n");
        System.out.println(combatResult.toString());  // Display once when combat starts

        while (combatState.isCombatActive() && combatState.getPlayer().isAlive() && combatState.getNpc().isAlive()) {
            CombatAction playerAction = null;

            // Prompt user for action
            System.out.println("Choose an action: ATTACK, DEFEND, FLEE, USEPOTION");

            // Validate the input for a valid action
            while (playerAction == null) {
                String playerActionInput = combatState.getPlayer().getInput();

                try {
                    playerAction = CombatAction.valueOf(playerActionInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid action. Please enter one of the following actions: ATTACK, DEFEND, FLEE, USEPOTION.");
                    System.out.println("Enter your action (ATTACK, DEFEND, FLEE or USEPOTION): ");
                }
            }

            // Process valid action
            String actionResult = processPlayerAction(combatState.getPlayer(), combatState.getNpc(), playerAction);
            System.out.println(actionResult);

            // Handle the flee action by breaking out of the loop and awaiting the next command
            if (playerAction == CombatAction.FLEE) {
                System.out.println("You have chosen to flee. Awaiting your next command...");
                break;
            }

            // Check if NPC is defeated after the player’s action
            if (!combatState.getNpc().isAlive()) {
                System.out.println(combatState.getNpc().getName() + " has been defeated!");
                System.out.println("Game Over. You have won!");
                System.exit(0);  // Exit the game after winning
            }

            // NPC's turn to attack if still alive
            String npcActionResult = npcTurn(combatState.getPlayer(), combatState.getNpc());
            System.out.println(npcActionResult);

            // Check if player is defeated
            if (!combatState.getPlayer().isAlive()) {
                System.out.println("You have been defeated by " + combatState.getNpc().getName() + ".");
                break;
            }
        }

        return new CommandResponse(combatResult.toString());
    }



    private String processPlayerAction(Player player, NonPlayableCharacter npc, CombatAction action) {
        switch (action) {
            case ATTACK:
                player.attack(npc);
                return "You attacked " + npc.getName() + " for " + player.getStrength() + " damage.\n";
            case DEFEND:
                return "You defended yourself.\n";
            case FLEE:
                return "You attempt to flee from combat.\n";
            case USEPOTION:
                player.usePotion();
                return "You used a potion to restore health.\n";
            default:
                return "Unknown action.\n";
        }
    }

    private String npcTurn(Player player, NonPlayableCharacter npc) {
        if (npc.isHostile()) {
            npc.attack(player);
            return npc.getName() + " attacks you for " + npc.getStrength() + " damage.\n";
        } else {
            return npc.getName() + " does not attack.\n";
        }
    }

    private boolean attemptToFlee(Player player, NonPlayableCharacter npc) {
        int playerAgility = player.getAgility();
        int npcAgility = npc.getAgility();
        return playerAgility >= npcAgility;
    }
}
