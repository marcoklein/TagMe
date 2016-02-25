/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.gameobject.logic;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.World;
import world.control.ObstacleControl;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class ObstacleLogic extends Logic {

    private Vector3f startLocation;
    private float initialSpeed;
    private Vector3f targetLocation;

    public ObstacleLogic() {
    }

    public ObstacleLogic(Vector3f startLocation, float initialSpeed, Vector3f targetLocation) {
        this.startLocation = startLocation;
        this.initialSpeed = initialSpeed;
        this.targetLocation = targetLocation;
    }
    
    
    @Override
    public void addLogic(World world, Node obstacle) {
        obstacle.setLocalTranslation(startLocation);
        obstacle.addControl(new ObstacleControl(initialSpeed, targetLocation));
        obstacle.addControl(new RigidBodyControl(0));
    }

    @Override
    public void removeLogic(World world, Node obstacle) {
        obstacle.removeControl(ObstacleControl.class);
        obstacle.removeControl(RigidBodyControl.class);
    }
    
}
