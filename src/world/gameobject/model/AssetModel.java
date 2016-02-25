package world.gameobject.model;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class AssetModel extends Model {
    
    private String modelName;

    public AssetModel() {
    }

    public AssetModel(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public Spatial createModel(World world) {
        return world.getApp().getAssetManager().loadModel(modelName);
    }
    
}
