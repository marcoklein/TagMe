package world;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import java.util.Random;
import java.util.logging.Logger;
import world.factory.ModelFactory;

/**
 * A world Spawner can be used to create and add GameObjects to the world
 * Some properties (color, size, etc.) can be specified
 * 
 * @author Marco Klein
 */
public class WorldSpawner {
    
    private World world;
    private Random random = new Random();
    
    
    private WorldSpawner(World world) {
        this.world = world;
    }
    
    public void spawnObstacle(Vector3f minPos, Vector3f maxPos, Vector3f minSize, Vector3f maxSize) {
        
    }
    
    public void spawnConstellation(Constellation constellation) {
        
    }
        
    
}
