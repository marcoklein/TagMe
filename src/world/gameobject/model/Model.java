package world.gameobject.model;

import com.jme3.scene.Spatial;
import java.io.Serializable;
import world.World;

/**
 *
 * @author Marco Klein
 */
public abstract class Model<T extends Spatial> implements Serializable {
    
    public abstract T createModel(World world);
}
