package network.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Sent to all clients which are connected when a new player enters the game.
 *
 * @author Marco Klein
 */
@Serializable
public class NewPlayerMessage extends AbstractMessage {
    
    private String playerName;
    private int playerId;

    public NewPlayerMessage() {
    }

    public NewPlayerMessage(String playerName, int playerId) {
        this.playerName = playerName;
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    
}
