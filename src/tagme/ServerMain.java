package tagme;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import com.jme3.system.JmeContext;
import java.util.Random;
import network.server.GameServer;
import world.World;

/**
 * Main of the Application.
 * 
 * TODO use GameStates instead and add a GUI to different screens (for the future)
 * 
 * @author Marco Klein
 */
public class ServerMain extends SimpleApplication {

    private BulletAppState bulletAppState;
    private World world;
    
    private Random random = new Random();

    @Override
    public void simpleInitApp() {
        GameServer server = new GameServer(new World(this, rootNode));
        stateManager.attach(server);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
    
    
    
    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless);
    }
}
