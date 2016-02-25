package network.client;

import com.jme3.app.state.AppStateManager;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.scene.Spatial;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import network.NetworkSerializer;
import network.message.IdentificationMessage;
import network.message.NewPlayerMessage;
import network.message.SetPlayerMessage;
import network.message.world.WorldMessage;
import world.World;

/**
 * Added to the client to enable network stuff.
 *
 * @author Marco Klein
 */
public class GameClient extends NetworkAppState implements MessageListener<Client> {
    private static final Logger LOG = Logger.getLogger(GameClient.class.getName());

    private Client client;
    private String host;
    private int port;
    
    private int playerId = -1;
    
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
        NetworkSerializer.registerClasses();
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        // initialize
        try {
            LOG.log(Level.INFO, "Connecting to server {0} on port {1}.", new Object[]{host, port});
            
            client = Network.connectToServer(GameServer.NAME, GameServer.VERSION, "localhost", GameServer.TCP_PORT, GameServer.UDP_PORT);
            
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        // add listeners
        client.addMessageListener(this);
        client.start();
        
        // send init message
        // TODO let user pick player name
        client.send(new IdentificationMessage("Zerstörer"));
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        LOG.info("Closing client.");
        // cleanup
        client.removeMessageListener(this);
        client.close();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        client.close();
    }
    

    @Override
    public void messageReceived(final Client source, final Message m) {
//        LOG.log(Level.INFO, "Message recieved: {0}", m);
        // handle incoming messages
        if (m instanceof WorldMessage) {
            world.getApp().enqueue(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    ((WorldMessage) m).applyToWorld(world);
                    return null;
                }
                
            });
        } else if (m instanceof SetPlayerMessage) {
            final SetPlayerMessage message = (SetPlayerMessage) m;
            world.getApp().enqueue(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    playerId = message.getId();
                    Spatial player = world.getGameObject(playerId);
                    player.addControl(new SyncPlayerControl(client));
                    return null;
                }
                
            });
            
            
            
        } else if (m instanceof NewPlayerMessage) {
            NewPlayerMessage msg = (NewPlayerMessage) m;
            LOG.log(Level.INFO, "Player {0} with id {1} has connected.", new Object[]{msg.getPlayerName(), msg.getPlayerId()});
        } else {
            LOG.log(Level.INFO, "Recieved invalid message: {0}", m.getClass());
        }
    }
    
    
    
}
