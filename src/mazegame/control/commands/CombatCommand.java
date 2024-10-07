package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;
import mazegame.entity.Location;
import mazegame.entity.items.Weapon;
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
	    CommandResponse combatResponse = startCombat(combatState);

	    // Check if the player fled
	    if (combatResponse.getMessage().contains("flee")) {
	        return new CommandResponse("Combat has ended as you chose to flee.");
	    }

	    return combatResponse;
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
        System.out.println(combatResult.toString());

        while (combatState.isCombatActive() && combatState.getPlayer().isAlive() && combatState.getNpc().isAlive()) {
            CombatAction playerAction = null;
            System.out.println("Choose an action: ATTACK, DEFEND, FLEE, USEPOTION");

            while (playerAction == null) {
                String playerActionInput = combatState.getPlayer().getInput();

                try {
                    playerAction = CombatAction.valueOf(playerActionInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid action. Please enter one of the following actions: ATTACK, DEFEND, FLEE, USEPOTION.");
                    System.out.println("Enter your action (ATTACK, DEFEND, FLEE or USEPOTION): ");
                }
            }

            String actionResult = processPlayerAction(combatState.getPlayer(), combatState.getNpc(), playerAction);
            System.out.println(actionResult);

            if (playerAction == CombatAction.FLEE) {
                System.out.println("You have chosen to flee. Combat has ended.");
                break;  // Break out of combat loop
            }

            if (!combatState.getNpc().isAlive()) {
                System.out.println(combatState.getNpc().getName() + " has been defeated!");
                System.out.println("Game Over. You have won!");
                System.exit(0);  // Exit the game after winning
            }

            // NPC's turn to attack if still alive
            String npcActionResult = npcTurn(combatState.getPlayer(), combatState.getNpc());
            System.out.println(npcActionResult);

            if (!combatState.getPlayer().isAlive()) {
                System.out.println("You have been defeated by " + combatState.getNpc().getName() + ".");
                System.out.println("Game Over. You have lost!");
                System.exit(0);  // Exit the game after player is defeated
            }
        }

        return new CommandResponse(combatResult.toString());
    }



    private String processPlayerAction(Player player, NonPlayableCharacter npc, CombatAction action) {
        switch (action) {
            case ATTACK:
                Weapon equippedWeapon = player.getEquippedWeapon();
                
                if (equippedWeapon == null) {
                    return "You are not equipped with any weapon. It's best to flee!";
                }

                int damage = equippedWeapon.getDamage();
                npc.takeDamage(damage);

                StringBuilder attackResult = new StringBuilder();
                attackResult.append("You attacked ").append(npc.getName())
                            .append(" with ").append(equippedWeapon.getLabel())
                            .append(" for ").append(damage).append(" damage.\n");

                attackResult.append("Current Stats:\n");
                attackResult.append("Player - Life Points: ").append(player.getLifePoints())
                            .append(", Equipped Weapon: ").append(equippedWeapon.getLabel())
                            .append(" (Damage: ").append(equippedWeapon.getDamage()).append(")\n");

                attackResult.append(npc.getName()).append(" - Life Points: ").append(npc.getLifePoints())
                            .append(npc.getLifePoints() <= 0 ? " (Defeated)" : "");

                return attackResult.toString();

            case DEFEND:
                return "You defended yourself.\n";
                
            case FLEE:
                return "You attempt to flee from combat.\n";

            case USEPOTION:
            	String potionResult = player.usePotion();
                return potionResult + " New Health: " + player.getLifePoints() + ".\n";

            default:
                return "Unknown action.\n";
        }
    }

    private String npcTurn(Player player, NonPlayableCharacter npc) {
        if (npc.isHostile()) {
            int npcDamage = npc.getStrength();
            player.takeDamage(npcDamage);
            return npc.getName() + " attacks you for " + npcDamage + " damage.\n";
        } else {
            return npc.getName() + " does not attack.\n";
        }
    }
}
