package world.controls;

import com.jme3.scene.control.AbstractControl;
import world.World;

/**
 *
 * @author Marco Klein
 */
public abstract class GameObjectControl extends AbstractControl {
    
    
    protected World world;

    public GameObjectControl() {
    }
    
    public GameObjectControl(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
}
