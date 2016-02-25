package network.message.world;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
public class UpdateGameObjectPosition extends GameObjectMessage {

    private Matrix3f rotation;
    private Vector3f location;

    public UpdateGameObjectPosition() {
    }

    public UpdateGameObjectPosition(Matrix3f rotation, Vector3f location, int id) {
        super(id);
        this.rotation = rotation;
        this.location = location;
    }

    @Override
    public void applyToGameObject(World world, Spatial gameObject) {
        gameObject.setLocalRotation(rotation);
        gameObject.setLocalTranslation(location);
    }
    
}
