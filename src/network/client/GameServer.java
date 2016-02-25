package network.client;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import network.message.IdentificationMessage;
import world.World;

/**
 * Holds clients which will be handled.
 * 
 * Player names are stored in the HostedConnection with the key "PlayerName"
 *
 * @author Marco Klein
 */
public class GameServer extends NetworkAppState implements MessageListener<HostedConnection> {
    private static final Logger LOG = Logger.getLogger(GameServer.class.getName());
    
    private Application app;
    private Server server;

    public GameServer(World world) {
        super(world);
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        app = stateManager.getApplication();
        // initialize
        try {
            server = Network.createServer("TagMe", 1, 6666, 6666);
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
    public void messageReceived(final HostedConnection source, final Message m) {
        if (m instanceof IdentificationMessage) {
            IdentificationMessage identification = (IdentificationMessage) m;
            // TODO check identification
            LOG.log(Level.INFO, "{0} has connected.", identification.getPlayerName());
            source.setAttribute("PlayerName", identification.getPlayerName());
            
            // identification accepted - let player join the game
            app.enqueue(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    return null;
                }
                
            });
        }
    }

    
    
}
