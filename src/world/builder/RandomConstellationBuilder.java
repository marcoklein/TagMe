package world.builder;

import world.builder.ObstacleBuilder;
import com.jme3.math.Vector3f;
import java.util.Random;
import world.Constellation;
import world.World;

/**
 *
 * @author Marco Klein
 */
public class RandomConstellationBuilder {
    private Random random = new Random();
    
    private Vector3f worldSize = null;
    private int obstacles = -1;
    private Vector3f minObstacleSize = null;
    private Vector3f maxObstacleSize = null;
    private float minInitialSpeed = 0;
    private float maxInitialSpeed = 0;
    
    public RandomConstellationBuilder worldSize(Vector3f worldSize) {
        this.worldSize = worldSize;
        return this;
    }
    
    public RandomConstellationBuilder obstacles(int obstacles) {
        this.obstacles = obstacles;
        return this;
    }
    
    public RandomConstellationBuilder obstacleSizeRange(Vector3f minSize, Vector3f maxSize) {
        this.minObstacleSize = minSize;
        this.maxObstacleSize = maxSize;
        return this;
    }
    
    public RandomConstellationBuilder obstacleInitialSpeedRange(float min, float max) {
        this.minInitialSpeed = min;
        this.maxInitialSpeed = max;
        return this;
    }
    
    /**
     * Builds up a world constallation by taking all set options into account.
     * 
     * @return 
     */
    public Constellation build(World world) {
        Constellation constellation = new Constellation();
        
        for (int i = 0; i < obstacles; i++) {
            constellation.addGameObject(new ObstacleBuilder()
                    .locationRange(new Vector3f(-worldSize.x, 0, -worldSize.z), worldSize)
                    .setSlideIn(false)
                    .sizeRange(minObstacleSize, maxObstacleSize)
                    .build(world)
                    );
        }
        
        return constellation;
    }
    
    
}
