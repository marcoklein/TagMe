/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.controls.DestroyableControl;
import world.controls.ObstacleControl;
import world.controls.PlayerControl;

/**
 * Creates GameObjects like Players or Obstacles.
 *
 * @author Marco Klein
 */
public class GameObjectFactory {

    /**
     * Creates a new Player by adding a PlayerControl and a player model
     * to the returned Spatial.
     * @return 
     */
    public static Spatial createPlayer(World world, Geometry geometry, Vector3f startLocation) {
        Node player = new Node();
        player.setLocalTranslation(startLocation);
        BetterCharacterControl characterControl = new BetterCharacterControl(0.3f, 2f, 1f);
        characterControl.setJumpForce(new Vector3f(0, 400f, 0)); 
        characterControl.setGravity(new Vector3f(0, 9.81f, 0));
//        RigidBodyControl characterControl = new RigidBodyControl(1f);
        player.addControl(characterControl);
        player.addControl(new PlayerControl(world, 300));
        player.attachChild(geometry);
        return player;
    }
    
    /**
     * Creates a new Obstacle using the given geometry.
     * Use ModelFactory.create* to create a geometry.
     * 
     * @param geometry
     * @return 
     */
    public static Spatial createObstacle(World world, Geometry geometry, float initialSpeed, Vector3f startTargetLocation) {
        Node obstacle = new Node();
        obstacle.attachChild(geometry);
        obstacle.addControl(new ObstacleControl(initialSpeed, startTargetLocation));
        obstacle.addControl(new DestroyableControl());
        RigidBodyControl rigidControl = new RigidBodyControl(1);
        obstacle.addControl(rigidControl);
        rigidControl.setKinematic(true);
        return obstacle;
    }
    
    
    
    
}
