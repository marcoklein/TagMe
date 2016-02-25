package network.gamemode;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Server;
import java.util.ArrayList;
import world.GameObjectControl;
import world.World;

/**
 * Manages all GameModes.
 *
 * @author Marco Klein
 */
public class GameModeManager extends AbstractAppState {
    
    private Server server;
    private World world;
    
    /**
     * Active GameMode.
     */
    private GameMode gameMode;
    
    private ArrayList<GameObjectControl> players = new ArrayList<>();

    public GameModeManager(Server server, World world) {
        this.server = server;
    }
    
    public void addPlayer(GameObjectControl player) {
        players.add(player);
        if (gameMode != null) {
            gameMode.playerJoined(player);
        }
    }
    
    public void removePlayer(GameObjectControl player) {
        players.remove(player);
        if (gameMode != null) {
            gameMode.playerLeft(player);
        }
    }

    public Server getServer() {
        return server;
    }

    public World getWorld() {
        return world;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
    
}
