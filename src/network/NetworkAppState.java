package network;

import com.jme3.app.state.AbstractAppState;
import world.World;

/**
 * Superclass for client and server.
 *
 * @author Marco Klein
 */
public class NetworkAppState extends AbstractAppState {
    
    protected World world;

    public NetworkAppState(World world) {
        this.world = world;
    }
    
    
    
}
