package mazegame.entity;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<NonPlayableCharacter> partyMembers = new ArrayList<>();

    public void addMember(NonPlayableCharacter npc) {
        if (!partyMembers.contains(npc)) {
            partyMembers.add(npc);
        }
    }

    public void removeMember(NonPlayableCharacter npc) {
        partyMembers.remove(npc);
    }

    public List<NonPlayableCharacter> getPartyMembers() {
        return partyMembers;
    }
    
    public void moveParty(Location oldLocation, Location newLocation) {
        for (NonPlayableCharacter npc : partyMembers) {
            oldLocation.getNPCs().remove(npc);
            newLocation.addNPC(npc);
        }
    }
}
