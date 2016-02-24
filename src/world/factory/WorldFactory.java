package world.factory;

import world.World;

/**
 * Abstract superclass for all factories which create game objects for the world.
 *
 * @author Marco Klein
 */
public abstract class WorldFactory {
    
    protected World world;

    public WorldFactory(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
    
}
