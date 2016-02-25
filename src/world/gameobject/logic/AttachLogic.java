package world.gameobject.logic;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.World;
import world.control.AttachControl;

/**
 * Will attach the GameObject to another GameObject.
 *
 * @author Marco Klein
 */
@Serializable
public class AttachLogic extends Logic {
    
    /**
     * Id of GameObject to attach to.
     */
    private int targetGameObjectId;

    public AttachLogic() {
    }

    public AttachLogic(int targetGameObjectId) {
        this.targetGameObjectId = targetGameObjectId;
    }

    @Override
    public void addLogic(World world, Node gameObject) {
        // get target GameObject
        gameObject.addControl(new AttachControl(world.getGameObject(targetGameObjectId)));
//        Node attach = world.getGameObject(targetGameObjectId);
//        attach.attachChild(gameObject);
    }

    @Override
    public void removeLogic(World world, Node gameObject) {
        // get target GameObject
//        Node attach = world.getGameObject(targetGameObjectId);
//        attach.detachChild(gameObject);
        gameObject.removeControl(AttachControl.class);
    }
    
}
