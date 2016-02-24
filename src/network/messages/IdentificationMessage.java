package network.messages;

import com.jme3.network.serializing.Serializable;

/**
 * Sent from the client to the server with user credentials.
 *
 * @author Marco Klein
 */
@Serializable
public class IdentificationMessage {
    
    private String playerName;

    public IdentificationMessage() {
    }

    public IdentificationMessage(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
}
