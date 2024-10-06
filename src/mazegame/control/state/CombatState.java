package mazegame.control.state;

import mazegame.entity.NonPlayableCharacter;
import mazegame.entity.Player;

public class CombatState {
    private Player player;
    private NonPlayableCharacter npc;
    private boolean isCombatActive;

    public CombatState(Player player, NonPlayableCharacter npc) {
        this.player = player;
        this.npc = npc;
        this.isCombatActive = true;
    }

    public Player getPlayer() {
        return player;
    }

    public NonPlayableCharacter getNpc() {
        return npc;
    }

    public boolean isCombatActive() {
        return isCombatActive;
    }

    public void endCombat() {
        isCombatActive = false;
    }
}
