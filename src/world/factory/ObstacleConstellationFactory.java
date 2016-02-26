package world.factory;

import com.jme3.math.Vector3f;
import world.Constellation;
import world.World;

/**
 * Creates constellations of obstacles.
 * 
 * Constellations consist out of several obstacles and normally form a big "thing"
 * for example a house.
 * 
 * Constellations are used to make worlds more interesting.
 *
 * @author Marco Klein
 */
public class ObstacleConstellationFactory extends AbstractWorldFactory {

    public ObstacleConstellationFactory(World world) {
        super(world);
    }
    
    
    public Constellation createCross() {
        ObstacleConstellationBuilder builder = new ObstacleConstellationBuilder();
        
        float width = 10;
        float height = 6;
        float depth = 10;
        
        
        // left wall
        builder.add(new Vector3f(0, 0, 0), new Vector3f(1, height, depth));
        // back wall
        builder.add(new Vector3f(), new Vector3f(width, height + 0.5f, 1));
        // right wall
//        builder.add(new Vector3f(width, 0, 0), new Vector3f(0.5f, height, depth));
        
        
        return builder.build(world);
    }
    
    /**
     * Creates a constellation using the given file.
     * 
     * @param constellationFile
     * @return 
     */
    public Constellation createConstellation(String constellationFile) {
        return null;
    }
    
}
