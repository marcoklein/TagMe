package network.message.world;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import world.World;

/**
 * Abstract super class for all messages which change the world.
 * 
 * Note that you have to add every implemented message to the NetworkSerializer
 * in order to use the message.
 *
 * @author Marco Klein
 */
@Serializable
public abstract class WorldMessage extends AbstractMessage {
    public abstract void applyToWorld(World world);
}
