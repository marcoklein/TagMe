/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import java.util.Random;
import world.factory.GameObjectFactory;
import world.factory.ModelFactory;

/**
 *
 * @author bidlingm
 */
public class ObstacleBuilder {
    private Random random = new Random();

    private Vector3f size;
    private Vector3f minSize, maxSize;
    private Vector3f location;
    private Vector3f minLocation, maxLocation;
    private float initialSpeed;
    private ColorRGBA color;
    private boolean slideIn;
    
    public ObstacleBuilder(){
    }

    public ObstacleBuilder sizeRange(Vector3f minSize, Vector3f maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        return this;
    }

    public ObstacleBuilder size(Vector3f size) {
        this.size = size;
        return this;
    }

    public ObstacleBuilder locationRange(Vector3f minLoc, Vector3f maxLoc) {
        this.minLocation = minLoc;
        this.maxLocation = maxLoc;
        return this;
    }

    public ObstacleBuilder location(Vector3f location) {
        this.location = location;
        return this;
    }
    
    public ObstacleBuilder initialSpeed(float speed) {
        this.initialSpeed = speed;
        return this;
    }

    public ObstacleBuilder setSlideIn(boolean slideIn) {
        this.slideIn = slideIn;
        return this;
    }

    public ObstacleBuilder setColor(ColorRGBA color) {
        this.color = color;
        return this;
    }

    public Spatial buildObstacle(World world) {

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
        
        GameObjectFactory factory = new GameObjectFactory(world);
        Geometry obstacleGeom = ModelFactory.createBox(world.getApp().getAssetManager(), obstacleSize.x, obstacleSize.y, obstacleSize.z, color);
        Spatial obstacle = factory.createObstacle(startLocation, targetLocation, obstacleGeom, initialSpeed);
        return obstacle;
    }
    
    public Geometry buildGeometry(World world) {
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
        
        Geometry obstacleGeom = ModelFactory.createBox(world.getApp().getAssetManager(), obstacleSize.x, obstacleSize.y, obstacleSize.z, color);
        return obstacleGeom;
    }

}
