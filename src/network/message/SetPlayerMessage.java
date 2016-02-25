package network.message;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Sent to the client to set the Game Object id of the player for the client.
 *
 * @author Marco Klein
 */
@Serializable
public class SetPlayerMessage extends AbstractMessage {
    
    private int id;
    
    private ColorRGBA color;
    private Vector3f startLocation;

    public SetPlayerMessage(int id, ColorRGBA color, Vector3f startLocation) {
        this.id = id;
        this.color = color;
        this.startLocation = startLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorRGBA getColor() {
        return color;
    }

    public void setColor(ColorRGBA color) {
        this.color = color;
    }

    public Vector3f getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Vector3f startLocation) {
        this.startLocation = startLocation;
    }

}
