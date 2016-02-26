package world.factory;

import world.World;

/**
 * Abstract superclass for all factories which create game objects for the world.
 *
 * @author Marco Klein
 */
public abstract class AbstractWorldFactory {
    
    protected World world;

    public AbstractWorldFactory(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
    
}
