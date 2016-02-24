package tagme;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
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
        
        // set up physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.setDebugEnabled(true);
        
        // set up world
        Node worldNode = new Node();
        rootNode.attachChild(worldNode);
        world = new World(this, bulletAppState, worldNode);
        world.addGameObject(ground);
        
        /******
         * TEST Game Objects - remove it not used
         *****/
        
        Geometry obstacleGeom = ModelFactory.createBox(assetManager, 3, 1, 3, ColorRGBA.Blue);
        Spatial obstacle = GameObjectFactory.createObstacle(obstacleGeom, 1, new Vector3f(0, 5, 0));
        world.addGameObject(obstacle);
        
        
        obstacleGeom = ModelFactory.createBox(assetManager, 6, 1, 3, ColorRGBA.Blue);
        obstacle = GameObjectFactory.createObstacle(obstacleGeom, 0, new Vector3f(12, 5, 1));
        world.addGameObject(obstacle);
        
        
        obstacleGeom = ModelFactory.createBox(assetManager, 3, 4, 3, ColorRGBA.Blue);
        obstacle = GameObjectFactory.createObstacle(obstacleGeom, 3, new Vector3f(-5, 5, -3));
        world.addGameObject(obstacle);
        
        Geometry playerGeom = ModelFactory.createSphere(assetManager, 1, ColorRGBA.Blue);
        Spatial player = GameObjectFactory.createPlayer(playerGeom, new Vector3f(0, 10, 0));
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
