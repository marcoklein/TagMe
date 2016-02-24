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
import java.util.Random;
import world.factory.GameObjectFactory;
import world.factory.ModelFactory;
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
    
    private Random random = new Random();

    @Override
    public void simpleInitApp() {
        // add a ground (for test only)
        Spatial ground = assetManager.loadModel("Models/newScene.j3o");
        ground.setLocalTranslation(100, 0, 100);
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
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        //bulletAppState.setDebugEnabled(true);
        stateManager.attach(bulletAppState);
        
        // set up world
        Node worldNode = new Node();
        rootNode.attachChild(worldNode);
        world = new World(this, bulletAppState, worldNode, new Vector3f(50, 10, 50));
        world.addGameObject(ground);
        

        /**
         * ****
         * TEST Game Objects - remove if not used
         *****/
        
        GameObjectFactory factory = new GameObjectFactory(world);
        
        initWorld(world, new Vector3f(50, 10, 50));
        
        Spatial player = factory.createPlayer(new Vector3f(0, 10, 0), ColorRGBA.Green);
        world.addGameObject(player);
    }
    
    private void initWorld(World world, Vector3f worldSize) {
        GameObjectFactory factory = new GameObjectFactory(world);
        
        for (int i = 0; i < 100; i++) {
            // create a random obstacle size
            Vector3f obstacleSize = new Vector3f();
            obstacleSize.x = (float) (random.nextInt(1000) + 10) / 100f;
            obstacleSize.y = (float) (random.nextInt(400) + 10) / 100f;
            obstacleSize.z = (float) (random.nextInt(1000) + 10) / 100f;
            spawnObstacle(world, factory, worldSize, obstacleSize);
        }
    }
    
    private Spatial spawnObstacle(World world, GameObjectFactory factory, Vector3f worldSize, Vector3f obstacleSize) {
        Vector3f targetPosition = new Vector3f();
        // get a random position
        targetPosition.x = (float) random.nextInt((int) (worldSize.x * 1000)) / 1000f;
        targetPosition.y = (float) random.nextInt((int) (worldSize.y * 1000)) / 1000f;
        targetPosition.z = (float) random.nextInt((int) (worldSize.z * 1000)) / 1000f;
        Geometry obstacleGeom = ModelFactory.createBox(assetManager, obstacleSize.x, obstacleSize.y, obstacleSize.z, ColorRGBA.Blue);
        Spatial obstacle = factory.createObstacle(new Vector3f(targetPosition.x, obstacleSize.y, targetPosition.z), targetPosition, obstacleGeom, 0.2f);
        // set start position of obstacle underneath the ground so it can rise up
        world.addGameObject(obstacle);
        return obstacle;
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
