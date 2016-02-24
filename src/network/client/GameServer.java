package network.client;

import com.jme3.app.state.AppStateManager;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import world.World;

/**
 * Holds clients which will be handled.
 *
 * @author Marco Klein
 */
public class GameServer extends NetworkAppState implements MessageListener<HostedConnection> {
    
    private Server server;

    public GameServer(World world) {
        super(world);
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        // initialize
        try {
            server = Network.createServer(6143);
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        // add listeners
        server.addMessageListener(this);
        
        server.start();
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        server.removeMessageListener(this);
        // cleanup
        server.close();
    }

    @Override
    public void messageReceived(HostedConnection source, Message m) {
    }

    
    
}
