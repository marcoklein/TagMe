package world;

import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;
import world.control.WorldControl;

/**
 * Holds all GameObjects.
 * If a Spatial is added the Controls of the spatials are checked and the spatial
 * gets added accordingly to ids controls.
 *
 * @author Marco Klein
 */
public class World {
    private static final Logger LOG = Logger.getLogger(World.class.getName());
    
    /**
     * Application object of the game holding the AssetManager and other
     * important stuff.
     */
    private Application app;
    /**
     * Physics space of the world.
     * All collision objects are added to it.
     */
    private BulletAppState bulletAppState;
    /**
     * All GameObjects mapped to their id.
     * Get a game object id by calling gameObject.getUserData("Id")
     */
    private HashMap<Integer, Node> gameObjects;
    
    private ArrayList<WorldListener> listeners;
    
    /**
     * All Game Objects are added to the world node.
     */
    private Node worldNode;
    
    private Vector3f worldSize;
    /**
     * Used to generate new game object ids.
     */
    private int gameObjectId;

    public World(Application app, Node worldNode) {
        this.app = app;
        this.worldNode = worldNode;
        initialize();
    }
    
    private void initialize() {
        worldNode.detachAllChildren();
        listeners = new ArrayList<>();
        gameObjects = new HashMap<>();
        worldSize = new Vector3f();
                
        // init physics
        app.getStateManager().detach(bulletAppState);
        bulletAppState = new BulletAppState();
        app.getStateManager().attach(bulletAppState);
        
    }
    
    public void reset() {
        initialize();
    }
    
    /**
     * Adds all given Game Objects and returns their associated ids in the same
     * order.
     * 
     * @param gameObjects
     * @return 
     */
    public int[] addGameObjects(Node[] gameObjects) {
        int ids[] = new int[gameObjects.length];
        for (int i = 0; i < gameObjects.length; i++) {
            ids[i] = addGameObject(gameObjects[i]);
        }
        return ids;
    }
    
    /**
     * Adds the given game object (in form of a Spatial) to the world.
     * The game object type is determined by the spatial classes.
     * 
     * @param gameObject 
     */
    public int addGameObject(Node gameObject) {
        Integer id;
        if ((id = gameObject.getUserData("Id")) != null) {
            return addGameObject(gameObject, id);
        }
        return addGameObject(gameObject, generateGameObjectId());
    }
    
    public int addGameObject(Node gameObject, int id) {
        WorldControl gameObjectEntity = gameObject.getControl(WorldControl.class);
        if (gameObjectEntity != null) {
            // set world of game object
            gameObjectEntity.setWorld(this);
        }
        
        // get the GameObjectControl or create it if it does not exist
        GameObjectControl gameObjectControl = gameObject.getControl(GameObjectControl.class);
        if (gameObjectControl == null) {
            LOG.info("GameObject with no GameObjectControl added.");
            gameObjectControl = new GameObjectControl(this);
            gameObject.addControl(gameObjectControl);
        }
        
        // set id of entity
        gameObject.setUserData("Id", id);
        gameObjectControl.setId(id);
        gameObjects.put(id, gameObject);
        
        worldNode.attachChild(gameObject);
        
        for (WorldListener listener : listeners) {
            listener.gameObjectAdded(gameObject);
        }
        
        LOG.info("GameObject added to world.");
        return id;
    }
    
    public int generateGameObjectId() {
        return gameObjectId++;
    }
    
    /**
     * Calls removeGameObject(gameObjects.get(id)).
     * 
     * @param id 
     */
    public void removeGameObject(int id) {
        removeGameObject(gameObjects.get(id));
    }
    
    /**
     * Removes the given game object.
     * 
     * @param entity 
     */
    public void removeGameObject(Spatial entity) {
        for (WorldListener listener : listeners) {
            listener.gameObjectRemoved(entity);
        }
        
        entity.removeFromParent();
        gameObjects.remove((int) entity.getUserData("Id"));
        bulletAppState.getPhysicsSpace().removeAll(entity);
        
    }
    
    public Node getGameObject(int id) {
        return gameObjects.get(id);
    }
    
    public GameObjectControl getGameObjectControl(int id) {
        return gameObjects.get(id).getControl(GameObjectControl.class);
    }
    
    public Node[] getGameObjects() {
        Collection<Node> collection = gameObjects.values();
        Node[] spatials = collection.toArray(new Node[collection.size()]);
        return spatials;
        
    }
    
    public void addListener(WorldListener listener) {
        listeners.add(listener);
    }

    public Application getApp() {
        return app;
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    public PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    public Node getWorldNode() {
        return worldNode;
    }

    public Vector3f getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Vector3f worldSize) {
        this.worldSize = worldSize;
    }
    
    
    
}
