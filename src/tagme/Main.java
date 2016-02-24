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
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.GameObjectFactory;
import world.ModelFactory;
import world.World;

/**
 * Main of the Application.
 * 
 * TODO use GameStates instead and add a GUI to different screens (for the future)
 * 
 * @author Marco Klein
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;
    private World world;

    @Override
    public void simpleInitApp() {
        // add a ground (for test only)
        Spatial ground = assetManager.loadModel("Models/newScene.j3o");
        ground.addControl(new RigidBodyControl(0));
        rootNode.attachChild(ground);
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

        // set up physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        //bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        //bulletAppState.setDebugEnabled(true);
        
        // set up world
        Node worldNode = new Node();
        rootNode.attachChild(worldNode);
        world = new World(this, bulletAppState, worldNode);
        world.addGameObject(ground);
        

        /**
         * ****
         * TEST Game Objects - remove if not used
         *****/
        
        GameObjectFactory factory = new GameObjectFactory(world);
        
        Geometry obstacleGeom = ModelFactory.createBox(assetManager, 3, 1, 3, ColorRGBA.Blue);
        Spatial obstacle = factory.createObstacle(obstacleGeom, 2, new Vector3f(0, 5, 0));
        world.addGameObject(obstacle);
        
        
        obstacleGeom = ModelFactory.createBox(assetManager, 6, 1, 3, ColorRGBA.Blue);
        obstacle = factory.createObstacle(obstacleGeom, 5, new Vector3f(12, 8, 1));
        world.addGameObject(obstacle);
        
        
        obstacleGeom = ModelFactory.createBox(assetManager, 3, 4, 3, ColorRGBA.Blue);
        obstacle = factory.createObstacle(obstacleGeom, 0.4f, new Vector3f(-5, 5, -3));
        world.addGameObject(obstacle);
        
        Spatial player = factory.createPlayer(new Vector3f(0, 10, 0), ColorRGBA.Green);
        world.addGameObject(player);
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
    
    
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}
