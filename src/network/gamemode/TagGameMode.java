package network.gamemode;

import com.jme3.network.Server;
import com.jme3.scene.Spatial;

/**
 *
 * @author Marco Klein
 */
public class TagGameMode extends GameMode {

    public TagGameMode(Server server) {
        super(server);
    }
    
    @Override
    public void playerCollision(Spatial playerA, Spatial playerB) {
    }
    
}
