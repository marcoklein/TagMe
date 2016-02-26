/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.builder;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.Random;
import world.GameObjectControl;
import world.World;
import world.gameobject.logic.ObstacleLogic;
import world.gameobject.model.ObstacleModel;

/**
 *
 * @author bidlingm
 */
public class RandomObstacleBuilder {
    private Random random = new Random();

    private Vector3f size;
    private Vector3f minSize, maxSize;
    private Vector3f location;
    private Vector3f minLocation, maxLocation;
    private float initialSpeed;
    private ColorRGBA color;
    private boolean slideIn;
    
    public RandomObstacleBuilder(){
    }

    public RandomObstacleBuilder sizeRange(Vector3f minSize, Vector3f maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        return this;
    }

    public RandomObstacleBuilder size(Vector3f size) {
        this.size = size;
        return this;
    }

    public RandomObstacleBuilder locationRange(Vector3f minLoc, Vector3f maxLoc) {
        this.minLocation = minLoc;
        this.maxLocation = maxLoc;
        return this;
    }

    public RandomObstacleBuilder location(Vector3f location) {
        this.location = location;
        return this;
    }
    
    public RandomObstacleBuilder initialSpeed(float speed) {
        this.initialSpeed = speed;
        return this;
    }

    public RandomObstacleBuilder setSlideIn(boolean slideIn) {
        this.slideIn = slideIn;
        return this;
    }

    public RandomObstacleBuilder setColor(ColorRGBA color) {
        this.color = color;
        return this;
    }

    public Node build(World world) {

        // set obstacle size
        Vector3f obstacleSize = new Vector3f();
        if(size != null){
            obstacleSize = size;
        }else if(minSize != null && maxSize != null) {
            obstacleSize.x = minSize.x + random.nextFloat() * (maxSize.x-minSize.x);
            obstacleSize.y = minSize.y + random.nextFloat() * (maxSize.y-minSize.y);
            obstacleSize.z = minSize.z + random.nextFloat() * (maxSize.z-minSize.z);
        }else {
            obstacleSize.x = 1 + random.nextFloat() * 2;
            obstacleSize.y = 1 + random.nextFloat() * 2;
            obstacleSize.z = 1 + random.nextFloat() * 2;
        }
        
        // set obstacle target position
        Vector3f targetLocation = new Vector3f();
        if(location != null){
            targetLocation = location;
        }else if(minLocation != null && maxLocation != null){
            targetLocation.x = minLocation.x + random.nextFloat() * (maxLocation.x-minLocation.x);
            targetLocation.y = minLocation.y + random.nextFloat() * (maxLocation.y-minLocation.y);
            targetLocation.z = minLocation.z + random.nextFloat() * (maxLocation.z-minLocation.z);
        }else {
            targetLocation.x = random.nextFloat() * world.getWorldSize().x;
            targetLocation.y = random.nextFloat() * world.getWorldSize().y;
            targetLocation.z = random.nextFloat() * world.getWorldSize().z;
        }
        
        // set obstacle starting position
        Vector3f startLocation = new Vector3f();
        startLocation.x = targetLocation.x;
        startLocation.z = targetLocation.z;
        
        if(slideIn) {
            startLocation.y = -obstacleSize.y;
        }else {
            startLocation.y = targetLocation.y;
        }
        
        //set obstacle color
        if(color != null) {
            color = ColorRGBA.Blue;
        }
        
        Node obstacle = new Node("Obstacle");
        GameObjectControl gameObjectControl = new GameObjectControl(world, new ObstacleModel(obstacleSize), new ObstacleLogic(startLocation, initialSpeed, targetLocation));
        obstacle.addControl(gameObjectControl);
        return obstacle;
    }

}
