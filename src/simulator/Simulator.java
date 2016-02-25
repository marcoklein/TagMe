package simulator;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.logging.Logger;
import world.World;
import world.control.ObstacleControl;
import world.control.PlayerControl;

/**
 * Simulates all Game Objects of the world.
 * 
 * It moves obtacles and players and simulates.
 * 
 * There is only collision detection between players and obstacles.
 * So players can walk over each other.
 * For obstacles and obstacles collision detection would make no sense since
 * they are part of the world.
 * 
 * The Simulator uses the WorldListener to know when new objects are added.
 * It checks added controls to determin which kind of Game Object has been added.
 * For example if it finds a PlayerControl it assuems the Game Object to be a player.
 *
 * @author Marco Klein
 */
public class Simulator extends AbstractAppState {
    private static final Logger LOG = Logger.getLogger(Simulator.class.getName());
    
    private World world;
    
    /**
     * All objects with the PlayerControl
     */
    private ArrayList<Spatial> players;
    /**
     * All objects with the ObstacleControl
     */
    private ArrayList<Spatial> obstacles;

    public Simulator(World world) {
        this.world = world;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        initialize();
    }
    
    private void initialize() {
        //world.addListener(this);
        players = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        updatePlayers();
        checkPlayerObstacleCollisions(tpf);
    }
    
    /**
     * Update all players by using the PlayerSimulationControl.
     */
    private void updatePlayers() {
        for (Spatial player : players) {
            PlayerSimulationControl playerControl = player.getControl(PlayerSimulationControl.class);
            // check if player is on the ground and apply the move direction
        }
    }
    
    /**
     * Checks collisions between all obstacles and players.
     * If a player stands on an obstacle the player will move with the obstacle
     * so it will not get stuck.
     */
    private void checkPlayerObstacleCollisions(float tpf) {
        CollisionResults results = new CollisionResults();
        for (Spatial player : players) {
            PlayerSimulationControl playerControl = player.getControl(PlayerSimulationControl.class);
            boolean collision = false;
            for (Spatial obstacle : obstacles) {
                if (player.collideWith(obstacle, results) > 0) {
                    // there is at least one collision
                    LOG.info("Collision detected.");
                    // move player out of obstacle
                    CollisionResult closest = results.getClosestCollision();
                    // move player back a little bit
                    Vector3f move = closest.getContactNormal().mult(playerControl.getWalkDirection().length() * tpf);
                    player.getLocalTranslation().addLocal(move);
                }
            }
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    public void gameObjectAdded(Spatial gameObject) {
        if (gameObject.getControl(PlayerControl.class) != null) {
            // add to players
            players.add(gameObject);
        } else if (gameObject.getControl(ObstacleControl.class) != null) {
            // add to obstacles
            obstacles.add(gameObject);
        }
    }

    public void gameObjectRemoved(Spatial gameObject) {
        // TODO if players stands on obstacle he has to fall down
        players.remove(gameObject);
        obstacles.remove(gameObject);
    }
    
    
}
