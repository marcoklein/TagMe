package tagme;

import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Random;
import network.client.GameClient;
import world.World;

/**
 * Main of the Application.
 * 
 * TODO use GameStates instead and add a GUI to different screens (for the future)
 * 
 * @author Marco Klein
 */
public class ClientMain extends SimpleApplication {

    private BulletAppState bulletAppState;
    private World world;
    
    private Random random = new Random();

    @Override
    public void simpleInitApp() {
        // add a ground (for test only)
        Spatial ground = assetManager.loadModel("Models/newScene.j3o");
        ground.setLocalTranslation(100, 0, 100);
        ground.addControl(new RigidBodyControl(0));
        flyCam.setMoveSpeed(40);
        flyCam.setEnabled(false);
        // remove the fly cam completly since it would interfere with the game
        stateManager.detach(stateManager.getState(FlyCamAppState.class));
        
        // hide cursor since player will always look around
        inputManager.setCursorVisible(false);
        
        // add a light
        AmbientLight light = new AmbientLight();
        light.setColor(ColorRGBA.White);
        rootNode.addLight(light);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        rootNode.addLight(sun);
        
        
        Node worldNode = new Node();
        rootNode.attachChild(worldNode);
        // add network app state
        GameClient client = new GameClient("localhost", 6666, new World(this, worldNode));
        stateManager.attach(client);
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
    
    
    
    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        app.start();
    }
}
