package world;

import com.jme3.math.Vector3f;
import java.util.Random;

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
