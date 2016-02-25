package network.message.world;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import world.World;

/**
 * Initializes a world by calling reset and setting the appropriate world size.
 *
 * @author Marco Klein
 */
@Serializable
public class InitWorldMessage extends WorldMessage {
    
    private Vector3f worldSize;

    public InitWorldMessage() {
    }

    public InitWorldMessage(Vector3f worldSize) {
        this.worldSize = worldSize;
    }
    
    @Override
    public void applyToWorld(World world) {
        world.reset();
        // TODO set world size
    }

    public Vector3f getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Vector3f worldSize) {
        this.worldSize = worldSize;
    }
    
}
