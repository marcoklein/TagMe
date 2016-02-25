/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.gameobject.model;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class ObstacleModel extends Model<Node> {

    private Vector3f size;

    public ObstacleModel() {
    }

    public ObstacleModel(Vector3f size) {
        this.size = size;
    }
    
    @Override
    public Node createModel(World world) {
        Node model = new Node("Model");
        Geometry box = new BoxModel(size).createModel(world);
        Material mat = world.getApp().getAssetManager().loadMaterial("Materials/obstacle.j3m");
        box.setMaterial(mat);
        model.attachChild(box);
        return model;
    }
    
}
