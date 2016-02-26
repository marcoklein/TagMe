package world.factory;

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
