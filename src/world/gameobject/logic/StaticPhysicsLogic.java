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
    public void applyLogic(World world, Node gameObject) {
        gameObject.addControl(new RigidBodyControl(0));
        
        world.getBulletAppState().getPhysicsSpace().addAll(gameObject);
    }
    
}
