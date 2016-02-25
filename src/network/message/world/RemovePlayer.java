package network.message.world;

import com.jme3.network.serializing.Serializable;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class RemovePlayer extends WorldMessage {
    
    private int id;

    public RemovePlayer() {
    }

    public RemovePlayer(int id) {
        this.id = id;
    }

    @Override
    public void applyToWorld(World world) {
        world.removeGameObject(id);
    }
    
}
