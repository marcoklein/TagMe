package network.message.world;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.GameObjectControl;
import world.World;

/**
 * Sets the position of a GameObject but looks for physical controls to set the
 * location properly.
 * 
 * @author Marco Klein
 */
@Serializable
public class SetGameObjectLocationMessage extends GameObjectMessage {
    
    private Vector3f location;

    public SetGameObjectLocationMessage() {
    }

    public SetGameObjectLocationMessage(Vector3f location, int id) {
        super(id);
        this.location = location;
    }


    @Override
    public void applyToGameObject(World world, Node gameObject, GameObjectControl gameObjectControl) {
        RigidBodyControl bodyControl = gameObject.getControl(RigidBodyControl.class);
        if (bodyControl != null) {
            bodyControl.getPhysicsLocation().set(location);
        } else {
            BetterCharacterControl character = gameObject.getControl(BetterCharacterControl.class);
            if (character != null) {
                character.warp(location);
            } else {
                gameObject.setLocalTranslation(location);
            }
        }
    }
    
}
