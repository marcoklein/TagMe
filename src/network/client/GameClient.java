package network.client;

import com.jme3.app.state.AppStateManager;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import world.World;

/**
 * Added to the client to enable network stuff.
 *
 * @author Marco Klein
 */
public class GameClient extends NetworkAppState implements MessageListener<Client> {

    private Client client;
    private String host;
    private int port;
    
    /**
     * Creates a Game Client which will - if attached - connect to a Game Server
     * and handle network traffic.
     * 
     * @param host
     * @param port
     * @param world 
     */
    public GameClient(String host, int port, World world) {
        super(world);
        this.host = host;
        this.port = port;
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        // initialize
        try {
            client = Network.connectToServer(host, port);
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        // add listeners
        client.addMessageListener(this);
        client.start();
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        // cleanup
        client.removeMessageListener(this);
        client.close();
    }

    @Override
    public void messageReceived(Client source, Message m) {
    }
    
    
    
}
