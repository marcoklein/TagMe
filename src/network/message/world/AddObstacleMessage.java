/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.ObstacleBuilder;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class AddObstacleMessage extends GameObjectMessage {
    
    private boolean slideIn;
    private Vector3f size;
    private Vector3f location;
    private Vector3f targetLocation;
    private ColorRGBA color;
    private float initialSpeed;

    public AddObstacleMessage() {
    }

    public AddObstacleMessage(boolean slideIn, Vector3f size, Vector3f location, Vector3f targetLocation, ColorRGBA color, float initialSpeed, int id) {
        super(id);
        this.slideIn = slideIn;
        this.size = size;
        this.location = location;
        this.targetLocation = targetLocation;
        this.color = color;
        this.initialSpeed = initialSpeed;
    }
    
    
    

    @Override
    public void applyToGameObject(World world, Spatial gameObject) {
        ObstacleBuilder builder = new ObstacleBuilder()
                .initialSpeed(initialSpeed)
                .location(targetLocation)
                .setColor(color)
                .setSlideIn(slideIn)
                .size(size);
        Spatial obstacle = builder.buildObstacle(world);
        if (location != null) {
            // set current location if one is given
            obstacle.setLocalTranslation(targetLocation);
        }
        world.addGameObject(obstacle, id);
    }
    
}
