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

        CombatState combatState = new CombatState(thePlayer, npc);
        return startCombat(combatState); // Start combat with the found NPC
    }

    private NonPlayableCharacter findNPCByName(Location location, String name) {
        for (NonPlayableCharacter npc : location.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null; // Return null if no NPC with the given name is found
    }

    private CommandResponse startCombat(CombatState combatState) {
        StringBuilder combatResult = new StringBuilder("Combat started with " + combatState.getNpc().getName() + "!\n");

        while (combatState.isCombatActive() && combatState.getPlayer().isAlive() && combatState.getNpc().isAlive()) {
            combatResult.append("\nChoose an action: ATTACK, DEFEND, FLEE\n");
			String playerActionInput = combatState.getPlayer().getInput();
            CombatAction playerAction;

            try {
                playerAction = CombatAction.valueOf(playerActionInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                combatResult.append("Invalid action. Please choose a valid action.\n");
                continue;
            }

            String actionResult = processPlayerAction(combatState.getPlayer(), combatState.getNpc(), playerAction);
            combatResult.append(actionResult);

            if (!combatState.getNpc().isAlive()) {
                combatResult.append(combatState.getNpc().getName() + " has been defeated!\n");
                break;
            }

            String npcActionResult = npcTurn(combatState.getPlayer(), combatState.getNpc());
            combatResult.append(npcActionResult);

            if (!combatState.getPlayer().isAlive()) {
                combatResult.append("You have been defeated by " + combatState.getNpc().getName() + ".\n");
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
            case FLEE:
                boolean escaped = attemptToFlee(player, npc);
                if (escaped) {
                    return "You successfully fled from combat.\n";
                } else {
                    return "Failed to flee! " + npc.getName() + " blocks your escape.\n";
                }
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
