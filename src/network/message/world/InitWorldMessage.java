package network.message.world;

import com.jme3.network.serializing.Serializable;
import world.World;

/**
 * Initializes a world by calling reset and setting the appropriate world size.
 *
 * @author Marco Klein
 */
@Serializable
public class InitWorldMessage extends WorldMessage {
    
    @Override
    public void applyToWorld(World world) {
        world.reset();
        // TODO set world size
    }
    
    
    
}
