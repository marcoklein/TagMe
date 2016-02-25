package world.gameobject.model;

import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
public abstract class Model<T extends Spatial> {
    
    public abstract T createModel(World world);
}
