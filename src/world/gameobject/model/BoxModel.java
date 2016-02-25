package world.gameobject.model;

import com.jme3.math.Vector3f;
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

    public BoxModel() {
    }

    public BoxModel(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public BoxModel(Vector3f size) {
        width = size.x;
        height = size.y;
        depth = size.z;
    }
    

    @Override
    public Geometry createModel(World world) {
        Box b = new Box(width, height, depth);
        Geometry geom = new Geometry("Box", b);
        return geom;
    }
    
}
