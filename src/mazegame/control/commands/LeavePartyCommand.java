package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Party;
import mazegame.entity.Player;

public class LeavePartyCommand implements Command {
    private Party party;

    public LeavePartyCommand(Party party) {
        this.party = party;
    }

    @Override
    public CommandResponse execute(ParsedInput userInput, Player player) {
        if (userInput.getArguments().isEmpty()) {
            return new CommandResponse("Please specify the NPC name to leave the party.");
        }

        String npcName = (String) userInput.getArguments().get(0);
        NonPlayableCharacter npc = findNPCInParty(npcName);

        if (npc == null) {
            return new CommandResponse(npcName + " is not in your party.");
        }

        party.removeMember(npc);
        return new CommandResponse(npc.getName() + " has left your party.");
    }

    private NonPlayableCharacter findNPCInParty(String name) {
        for (NonPlayableCharacter npc : party.getPartyMembers()) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
