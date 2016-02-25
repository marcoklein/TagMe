package network.gamemode;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Server;
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

    public GameModeManager(Server server, World world) {
        this.server = server;
    }
    
    
}
