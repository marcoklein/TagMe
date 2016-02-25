package world.gameobject.logic;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.World;

/**
 * Adds a RigidBodyControl with mass 0 to the GameObject.
 *
 * @author Marco Klein
 */
@Serializable
public class StaticPhysicsLogic extends Logic {

    @Override
    public void addLogic(World world, Node gameObject) {
        RigidBodyControl bodyControl = new RigidBodyControl(0);
        gameObject.addControl(bodyControl);
        
        world.getBulletAppState().getPhysicsSpace().add(bodyControl);
    }

    @Override
    public void removeLogic(World world, Node gameObject) {
        RigidBodyControl bodyControl = gameObject.getControl(RigidBodyControl.class);
        gameObject.removeControl(bodyControl);
        
        world.getBulletAppState().getPhysicsSpace().remove(bodyControl);
    }
    
}
