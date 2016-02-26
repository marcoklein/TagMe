package world.gameobject.model;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class GroundModel extends Model {
    
    private Vector3f size;

    public GroundModel() {
    }

    public GroundModel(Vector3f size) {
        this.size = size;
    }

    @Override
    public Spatial createModel(World world) {
        Node model = new Node("Model");
        Geometry box = new BoxModel(size).createModel(world);
        Material mat = world.getApp().getAssetManager().loadMaterial("Materials/ground.j3m");
        box.setMaterial(mat);
        model.attachChild(box);
        return model;
    }
    
}
