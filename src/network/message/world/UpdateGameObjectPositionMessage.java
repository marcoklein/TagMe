package network.message.world;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.GameObjectControl;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class UpdateGameObjectPositionMessage extends GameObjectMessage {

    private Quaternion rotation;
    private Vector3f location;

    public UpdateGameObjectPositionMessage() {
    }

    public UpdateGameObjectPositionMessage(Spatial gameObject) {
        super((int) gameObject.getUserData("Id"), false);
        this.rotation = ((Node) gameObject).getChild("Model").getLocalRotation();
        this.location = gameObject.getLocalTranslation();
    }
    
    public UpdateGameObjectPositionMessage(Quaternion rotation, Vector3f location, int id) {
        super(id, false);
        this.rotation = rotation;
        this.location = location;
    }

    @Override
    public void applyToGameObject(World world, Node gameObject, GameObjectControl gameObjectControl) {
        if (rotation != null) {
            gameObjectControl.getModelNode().setLocalRotation(rotation);
        }
        if (location != null) {
            gameObject.setLocalTranslation(location);
        }
    }
    
}
