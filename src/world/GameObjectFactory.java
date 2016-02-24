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
    public static Spatial createPlayer(Geometry geometry, Vector3f startLocation) {
        Node player = new Node();
        player.setLocalTranslation(startLocation);
        player.addControl(new BetterCharacterControl(1, 1, 50));
        player.addControl(new PlayerControl());
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
    public static Spatial createObstacle(Geometry geometry, float initialSpeed, Vector3f startTargetLocation) {
        Node obstacle = new Node();
        obstacle.addControl(new ObstacleControl(initialSpeed, startTargetLocation));
        obstacle.addControl(new DestroyableControl());
        obstacle.addControl(new RigidBodyControl(0));
        obstacle.attachChild(geometry);
        return obstacle;
    }
    
    
    
    
}
