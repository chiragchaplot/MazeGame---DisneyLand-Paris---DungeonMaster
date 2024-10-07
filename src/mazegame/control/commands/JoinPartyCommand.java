package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Location;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Party;
import mazegame.entity.Player;

public class JoinPartyCommand implements Command {
    private Party party;

    public JoinPartyCommand(Party party) {
        this.party = party;
    }

    @Override
    public CommandResponse execute(ParsedInput userInput, Player player) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the NPC name to join the party.");
        }

        String npcName = (String) userInput.getArguments().get(0);
        NonPlayableCharacter npc = findNPCByName(player.getCurrentLocation(), npcName);

        if (npc == null) {
            return new CommandResponse("No NPC found with the name: " + npcName);
        }

        if (npc.isHostile()) {
            return new CommandResponse(npc.getName() + " is hostile and cannot join the party.");
        }

        if (party.getPartyMembers().contains(npc)) {
            return new CommandResponse(npc.getName() + " is already in your party.");
        }

        party.addMember(npc);
        return new CommandResponse(npc.getName() + " has joined your party!");
    }

    private NonPlayableCharacter findNPCByName(Location location, String name) {
        for (NonPlayableCharacter npc : location.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
