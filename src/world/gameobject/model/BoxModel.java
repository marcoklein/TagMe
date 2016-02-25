package world.gameobject.model;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import world.World;

/**
 *
 * @author Marco Klein
 */
public class BoxModel extends Model<Geometry> {
    
    protected float width;
    protected float height;
    protected float depth;

    @Override
    public Geometry createModel(World world) {
        Box b = new Box(width, height, depth);
        Geometry geom = new Geometry("Box", b);
        return geom;
    }
    
}
