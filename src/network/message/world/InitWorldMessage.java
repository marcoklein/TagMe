package network.message.world;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;

/**
 * Initializes a world by calling reset and setting the appropriate world size.
 *
 * @author Marco Klein
 */
@Serializable
public class InitWorldMessage extends WorldMessage {
    
    private Vector3f worldSize;
    
    /**
     * Initial Game Objects.
     */
    private Spatial[] gameObjects;

    public InitWorldMessage() {
    }

    public InitWorldMessage(Vector3f worldSize, Spatial[] gameObjects) {
        this.worldSize = worldSize;
        this.gameObjects = gameObjects;
    }

    
    @Override
    public void applyToWorld(World world) {
        world.reset();
        // world size
        world.setWorldSize(worldSize);
        // add initial game objects
        world.addGameObjects(gameObjects);
    }

    public Vector3f getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Vector3f worldSize) {
        this.worldSize = worldSize;
    }

    public Spatial[] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(Spatial[] gameObjects) {
        this.gameObjects = gameObjects;
    }
    
}
