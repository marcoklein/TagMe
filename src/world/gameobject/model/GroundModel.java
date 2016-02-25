package world.gameobject.model;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class GroundModel extends Model {

    @Override
    public Spatial createModel(World world) {
        // TODO check world size to create ground
        Spatial ground = world.getApp().getAssetManager().loadModel("Models/newScene.j3o");
        ground.setLocalTranslation(100, 0, 100);
        return ground;
    }
    
}
