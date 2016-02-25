package network.message;

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

    public SetPlayerMessage() {
    }

    public SetPlayerMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
