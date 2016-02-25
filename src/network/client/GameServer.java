package network.client;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.ConnectionListener;
import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import network.NetworkSerializer;
import network.message.IdentificationMessage;
import network.message.NewPlayerMessage;
import network.message.SetPlayerMessage;
import network.message.world.InitWorldMessage;
import network.message.world.UpdateGameObjectPosition;
import network.message.world.WorldMessage;
import world.World;

/**
 * Holds clients which will be handled.
 * 
 * Player names are stored in the HostedConnection with the key "PlayerName"
 *
 * @author Marco Klein
 */
public class GameServer extends NetworkAppState implements MessageListener<HostedConnection>, ConnectionListener {
    private static final Logger LOG = Logger.getLogger(GameServer.class.getName());
    public static final String NAME = "TagMe";
    public static final int VERSION = 1;
    public static final int PORT = 5110;
    
    private Application app;
    private Server server;
    
    private ArrayList<HostedConnection> identifiedConnections = new ArrayList<>();

    public GameServer(World world) {
        super(world);
        NetworkSerializer.registerClasses();
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        int port = 5110;
        app = stateManager.getApplication();
        // initialize
        try {
            server = Network.createServer(NAME, VERSION, PORT, PORT);
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        server.start();
        
        // add listeners
        server.addMessageListener(this);
        LOG.log(Level.INFO, "Server created and started on port {0}", port);
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        server.removeMessageListener(this);
        // cleanup
        server.close();
    }

    @Override
    public void messageReceived(final HostedConnection source, final Message m) {
        if (m instanceof UpdateGameObjectPosition) {
            // broadcast location updates
            // TODO test if client is allowed to update object
            server.broadcast(Filters.notEqualTo(source), m);
        } if (m instanceof IdentificationMessage) {
            final IdentificationMessage identification = (IdentificationMessage) m;
            // TODO check identification
            LOG.log(Level.INFO, "{0} has connected.", identification.getPlayerName());
            source.setAttribute("PlayerName", identification.getPlayerName());
            source.setAttribute("Identified", true);
            
            // identification accepted - let player join the game
            app.enqueue(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    // add a player model to the client
                    // prepare the init world message
                    InitWorldMessage message = new InitWorldMessage(world.getWorldSize(), world.getGameObjects());
                    source.send(message);
                    int id = world.generateGameObjectId();
                    source.setAttribute("PlayerId", id);
                    
                    // let user know who his player is
                    SetPlayerMessage playerMsg = new SetPlayerMessage(id, ColorRGBA.White, new Vector3f(0, 50, 0));
                    source.send(playerMsg);
                    
                    // inform other players about player
                    server.broadcast(Filters.notEqualTo(source), new NewPlayerMessage(identification.getPlayerName(), id));
                    LOG.info("Player joined game.");
                    return null;
                }
                
            });
        } else if (source.getAttribute("Identified")) {
            // only proceed message if hosted connection is identified
            if (m instanceof WorldMessage) {
                ((WorldMessage) m).applyToWorld(world);
            }
        }
    }

    @Override
    public void connectionAdded(Server server, HostedConnection conn) {
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection conn) {
        // remove player
        if (identifiedConnections.contains(conn)) {
            identifiedConnections.remove(conn);
            world.removeGameObject((int) conn.getAttribute("PlayerId"));
        }
    }

    
    
}
