package network.client;

import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkAppState;
import network.NetworkSerializer;
import network.message.IdentificationMessage;
import network.message.NewPlayerMessage;
import network.message.SetPlayerMessage;
import network.message.world.WorldMessage;
import world.World;
import world.control.PlayerControl;
import world.factory.ModelFactory;

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
            
            client = Network.connectToServer(GameServer.NAME, GameServer.VERSION, "localhost", GameServer.PORT, GameServer.PORT);
            
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
        // cleanup
        client.removeMessageListener(this);
        client.close();
    }

    @Override
    public void messageReceived(Client source, Message m) {
        LOG.log(Level.INFO, "Message recieved: {0}", m);
        // handle incoming messages
        if (m instanceof WorldMessage) {
            ((WorldMessage) m).applyToWorld(world);
        } else if (m instanceof SetPlayerMessage) {
            SetPlayerMessage message = (SetPlayerMessage) m;
            playerId = message.getId();
            
            // add player controls
            Node player = (Node) world.getGameObject(message.getId());
            // move player to start location
            player.setLocalTranslation(message.getStartLocation());
            // add physics to player
            BetterCharacterControl characterControl = new BetterCharacterControl(1f, 2f, 1f);
            characterControl.setJumpForce(new Vector3f(0, 11f, 0));
            player.addControl(characterControl);
            player.addControl(new PlayerControl(world, 8));

            // set up geometry
            Geometry geometry = ModelFactory.createSphere(world.getApp().getAssetManager(), 1, message.getColor());
            // move geometry in center of player
            geometry.setLocalTranslation(0, 1f, 0);
            geometry.setName("PlayerGeometry");
            player.attachChild(geometry);

            // attach a camera
            ChaseCamera cam = new ChaseCamera(world.getApp().getCamera(), geometry, world.getApp().getInputManager());
            cam.setDragToRotate(false);
            cam.setInvertVerticalAxis(true);

            
        } else if (m instanceof NewPlayerMessage) {
            NewPlayerMessage msg = (NewPlayerMessage) m;
            LOG.log(Level.INFO, "Player {0} with id {1} has connected.", new Object[]{msg.getPlayerName(), msg.getPlayerId()});
        } else {
            LOG.log(Level.INFO, "Recieved invalid message: {0}", m.getClass());
        }
    }
    
    
    
}
