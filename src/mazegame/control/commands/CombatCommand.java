package mazegame.control.commands;

import java.util.ArrayList;
import java.util.List;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Party;
import mazegame.entity.Player;
import mazegame.entity.Location;
import mazegame.entity.items.Weapon;
import mazegame.control.state.CombatState;

public class CombatCommand implements Command {
    private Party party;

    public CombatCommand(Party party) {
        this.party = party;
    }

    @Override
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
        return location.getNPCs().stream()
                .filter(npc -> npc.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    private CommandResponse startCombat(CombatState combatState) {
        StringBuilder combatResult = new StringBuilder("Combat started with " + combatState.getNpc().getName() + "!\n");

        while (combatState.isCombatActive() && combatState.getPlayer().isAlive() && combatState.getNpc().isAlive()) {
            CombatAction playerAction = null;
            System.out.println("Choose an action: ATTACK, DEFEND, FLEE, USEPOTION");

            while (playerAction == null) {
                String playerActionInput = combatState.getPlayer().getInput();

                try {
                    playerAction = CombatAction.valueOf(playerActionInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid action. Please enter one of the following actions: ATTACK, DEFEND, FLEE, USEPOTION.");
                }
            }

            String actionResult = processPlayerAction(combatState.getPlayer(), combatState.getNpc(), playerAction);
            System.out.println(actionResult);

            if (playerAction == CombatAction.FLEE) {
                return new CommandResponse("You have chosen to flee. Combat has ended.");
            }

            if (!combatState.getNpc().isAlive()) {
                return new CommandResponse(combatState.getNpc().getName() + " has been defeated! You won the combat.");
            }

            String npcActionResult = npcTurn(combatState.getPlayer(), combatState.getNpc());
            System.out.println(npcActionResult);

            if (!combatState.getPlayer().isAlive()) {
                return new CommandResponse("You have been defeated by " + combatState.getNpc().getName() + ". Game Over.");
            }
        }

        return new CommandResponse(combatResult.toString());
    }

    public String processPlayerAction(Player player, NonPlayableCharacter npc, CombatAction action) {
        StringBuilder attackResult = new StringBuilder();

        switch (action) {
            case ATTACK:
                if (player.getEquippedWeapon() == null) {
                    return "You are not equipped with any weapon. It's best to flee!";
                }

                int playerDamage = player.getEquippedWeapon().getDamage();
                npc.takeDamage(playerDamage);
                attackResult.append("You attacked ").append(npc.getName())
                            .append(" with ").append(player.getEquippedWeapon().getLabel())
                            .append(" for ").append(playerDamage).append(" damage.\n");

                for (NonPlayableCharacter partyMember : party.getPartyMembers()) {
                    if (partyMember.getEquippedWeapon() != null) {
                        int memberDamage = partyMember.getEquippedWeapon().getDamage();
                        npc.takeDamage(memberDamage);
                        attackResult.append(partyMember.getName()).append(" attacked ").append(npc.getName())
                                    .append(" with ").append(partyMember.getEquippedWeapon().getLabel())
                                    .append(" for ").append(memberDamage).append(" damage.\n");
                    } else {
                        attackResult.append(partyMember.getName()).append(" has no weapon and cannot attack.\n");
                    }
                }

                attackResult.append("Current Stats:\n");
                attackResult.append("Player - Life Points: ").append(player.getLifePoints())
                            .append(", Equipped Weapon: ").append(player.getEquippedWeapon().getLabel())
                            .append(" (Damage: ").append(player.getEquippedWeapon().getDamage()).append(")\n");

                attackResult.append(npc.getName()).append(" - Life Points: ").append(npc.getLifePoints())
                            .append(npc.getLifePoints() <= 0 ? " (Defeated)" : "");

                return attackResult.toString();

            case DEFEND:
                return "You defended yourself.\n";
                    
            case FLEE:
                return "You attempt to flee from combat.\n";

            case USEPOTION:
                return player.usePotion() + " New Health: " + player.getLifePoints() + ".\n";

            default:
                return "Unknown action.\n";
        }
    }


    private String npcTurn(Player player, NonPlayableCharacter npc) {
        int npcDamage = npc.getStrength();
        int numPartyMembers = party.getPartyMembers().size() + 1;
        int damagePerMember = npcDamage / numPartyMembers;

        StringBuilder result = new StringBuilder(npc.getName() + " attacks the party! Each member takes " + damagePerMember + " damage.\n");

        player.takeDamage(damagePerMember);
        result.append("Player takes ").append(damagePerMember).append(" damage. Remaining life: ").append(player.getLifePoints()).append("\n");

        List<NonPlayableCharacter> defeatedMembers = new ArrayList<>();

        for (NonPlayableCharacter partyMember : party.getPartyMembers()) {
            partyMember.takeDamage(damagePerMember);
            result.append(partyMember.getName()).append(" takes ").append(damagePerMember).append(" damage. Remaining life: ").append(partyMember.getLifePoints()).append("\n");

            if (!partyMember.isAlive()) {
                result.append(partyMember.getName()).append(" has been defeated.\n");
                defeatedMembers.add(partyMember);
            }
        }

        for (NonPlayableCharacter defeatedMember : defeatedMembers) {
            party.removeMember(defeatedMember);
        }

        return result.toString();
    }

}
