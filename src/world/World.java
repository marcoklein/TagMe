package world;

import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import world.control.PlayerControl;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.control.WorldControl;
import world.control.WorldObjectControl;

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
     * List of all active players.
     */
    private ArrayList<Spatial> players;
    
    /**
     * List of all world objects f.e. obstacles or items.
     * I.e. everything that interacts with a player (block him or give him effects)
     */
    private ArrayList<Spatial> worldObjects;
    
    /**
     * All GameObjects mapped to their id.
     * Get a game object id by calling gameObject.getUserData("Id")
     */
    private HashMap<Integer, Spatial> gameObjects;
    
    private ArrayList<WorldListener> listeners;
    
    /**
     * All Game Objects are added to the world node.
     */
    private Node worldNode;
    
    private Vector3f worldSize;
    
    private int gameObjectId;

    public World(Application app, Node worldNode) {
        this.app = app;
        this.worldNode = worldNode;
        initialize();
    }
    
    private void initialize() {
        worldNode.detachAllChildren();
        players = new ArrayList<>();
        worldObjects = new ArrayList<>();
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
    public int[] addGameObjects(Spatial[] gameObjects) {
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
    public int addGameObject(Spatial gameObject) {
        Integer id;
        if ((id = gameObject.getUserData("Id")) != null) {
            return addGameObject(gameObject, id);
        }
        return addGameObject(gameObject, generateGameObjectId());
    }
    
    public int addGameObject(Spatial gameObject, int id) {
        WorldControl gameObjectEntity = gameObject.getControl(WorldControl.class);
        if (gameObjectEntity != null) {
            // set world of game object
            gameObjectEntity.setWorld(this);
        }
        if (gameObject.getControl(PlayerControl.class) != null) {
            // add player with PlacerControl
            bulletAppState.getPhysicsSpace().add(gameObject.getControl(BetterCharacterControl.class));
            bulletAppState.getPhysicsSpace().addAll(gameObject);
            players.add(gameObject);
        } else if (gameObject.getControl(WorldObjectControl.class) != null) {
            // add obstacle with ObstacleControl
            RigidBodyControl bodyControl = gameObject.getControl(RigidBodyControl.class);
            if (bodyControl != null) {
                bulletAppState.getPhysicsSpace().add(bodyControl);
                bulletAppState.getPhysicsSpace().addAll(gameObject);
            } else {
                LOG.warning("Obstacle Game Object with no RigidBodyControl added.");
            }
            worldObjects.add(gameObject);
        } else if (gameObject.getControl(RigidBodyControl.class) != null) {
            bulletAppState.getPhysicsSpace().addAll(gameObject);
        } else {
            // invalid Game Object
            LOG.log(Level.WARNING, "Could not add Game Object {0} because no appropriate control could be found - entity is no Game Object.", gameObject);
            return -1;
        }
        
        for (WorldListener listener : listeners) {
            listener.gameObjectAdded(gameObject);
        }
        
        // set id of entity
        gameObject.setUserData("Id", id);
        gameObjects.put(id, gameObject);

        worldNode.attachChild(gameObject);
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
        players.remove(entity);
        worldObjects.remove(entity);
        entity.removeFromParent();
        gameObjects.remove((int) entity.getUserData("Id"));
        bulletAppState.getPhysicsSpace().remove(entity);
        
        for (WorldListener listener : listeners) {
            listener.gameObjectRemoved(entity);
        }
    }
    
    public Spatial getGameObject(int id) {
        return gameObjects.get(id);
    }
    
    public Spatial[] getGameObjects() {
        Collection<Spatial> collection = gameObjects.values();
        Spatial[] spatials = collection.toArray(new Spatial[collection.size()]);
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

    public ArrayList<Spatial> getPlayers() {
        return players;
    }

    public ArrayList<Spatial> getWorldObjects() {
        return worldObjects;
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
