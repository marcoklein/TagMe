package network.gamemode;

import com.jme3.network.Server;
import com.jme3.scene.Spatial;

/**
 * Marco Klein
 *
 * @author Marco Klein
 */
public abstract class GameMode {
    
    protected Server server;

    public GameMode(Server server) {
        this.server = server;
    }
    
    
    public abstract void playerCollision(Spatial playerA, Spatial playerB);
    
    
}
