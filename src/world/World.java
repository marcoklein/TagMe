package world;

import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import world.controls.PlayerControl;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.controls.GameObjectControl;
import world.controls.WorldObjectControl;

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
     * The Size of the World
     */
    private Vector3f worldSize;
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
     * All Game Objects are added to the world node.
     */
    private Node worldNode;
    
    private int gameObjectId;

    public World(Application app, BulletAppState bulletAppState, Node worldNode, Vector3f size) {
        this.app = app;
        this.bulletAppState = bulletAppState;
        this.worldNode = worldNode;
        this.worldSize = size;
        initialize();
    }
    
    public void initialize() {
        worldNode.detachAllChildren();
        players = new ArrayList<>();
        worldObjects = new ArrayList<>();
    }
    
    public void reset() {
        initialize();
    }
    
    /**
     * Adds the given game object (in form of a Spatial) to the world.
     * The game object type is determined by the spatial classes.
     * 
     * @param entity 
     */
    public void addGameObject(Spatial entity) {
        GameObjectControl gameObjectEntity = entity.getControl(GameObjectControl.class);
        if (gameObjectEntity != null) {
            // set world of game object
            gameObjectEntity.setWorld(this);
        }
        if (entity.getControl(PlayerControl.class) != null) {
            // add player with PlacerControl
            bulletAppState.getPhysicsSpace().add(entity.getControl(BetterCharacterControl.class));
            bulletAppState.getPhysicsSpace().addAll(entity);
            players.add(entity);
        } else if (entity.getControl(WorldObjectControl.class) != null) {
            // add obstacle with ObstacleControl
            RigidBodyControl bodyControl = entity.getControl(RigidBodyControl.class);
            if (bodyControl != null) {
                bulletAppState.getPhysicsSpace().add(bodyControl);
                bulletAppState.getPhysicsSpace().addAll(entity);
            } else {
                LOG.warning("Obstacle Game Object with no RigidBodyControl added.");
            }
            worldObjects.add(entity);
        } else if (entity.getControl(RigidBodyControl.class) != null) {
            bulletAppState.getPhysicsSpace().addAll(entity);
        } else {
            // invalid Game Object
            LOG.log(Level.WARNING, "Could not add Game Object {0} because no appropriate control could be found - entity is no Game Object.", entity);
            return;
        }
        
        for (WorldListener listener : listeners) {
            listener.gameObjectAdded(entity);
        }
        
        // set id of entity
        entity.setUserData("Id", generateGameObjectId());

        worldNode.attachChild(entity);
    }
    
    private int generateGameObjectId() {
        return gameObjectId++;
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
        bulletAppState.getPhysicsSpace().remove(entity);
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
    
}
