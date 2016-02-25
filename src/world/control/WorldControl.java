package world.control;

import com.jme3.scene.control.AbstractControl;
import world.World;

/**
 *
 * @author Marco Klein
 */
public abstract class WorldControl extends AbstractControl {
    
    
    protected World world;

    public WorldControl() {
    }
    
    public WorldControl(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
}
