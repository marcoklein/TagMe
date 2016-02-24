/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.ChaseCamera;
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
    
    private World world;

    public GameObjectFactory(World world) {
        this.world = world;
    }
    
    /**
     * Creates a new Player by adding a PlayerControl and a player model
     * to the returned Spatial.
     * 
     * Additionally player properties are defined.
     * 
     * @return 
     */
    public Spatial createPlayer(Geometry geometry, Vector3f startLocation) {
        Node player = new Node();
        // move player to start location
        player.setLocalTranslation(startLocation);
        // move geometry in center of player
        geometry.setLocalTranslation(0, 1.5f, 0);
        // add physics to player
        BetterCharacterControl characterControl = new BetterCharacterControl(0.8f, 3f, 1f);
        characterControl.setJumpForce(new Vector3f(0, 10f, 0));
//        RigidBodyControl characterControl = new RigidBodyControl(1f);
        player.addControl(characterControl);
        player.addControl(new PlayerControl(world, 300));
        player.attachChild(geometry);
        // attach a camera
        
//        CameraNode camNode = new CameraNode("Player Camera", world.getApp().getCamera());
//        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
//        player.attachChild(camNode);
//        camNode.setLocalTranslation(0, 5, -5);
//        camNode.lookAt(player.getLocalTranslation(), Vector3f.UNIT_Y);
        new ChaseCamera(world.getApp().getCamera(), geometry, world.getApp().getInputManager());
        return player;
    }
    
    /**
     * Creates a new Obstacle using the given geometry.
     * Use ModelFactory.create* to create a geometry.
     * 
     * @param geometry
     * @return 
     */
    public Spatial createObstacle(Geometry geometry, float initialSpeed, Vector3f startTargetLocation) {
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
