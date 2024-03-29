/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.builder;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import world.Constellation;
import world.GameObjectControl;
import world.World;
import world.gameobject.logic.StaticPhysicsLogic;
import world.gameobject.model.GroundModel;

/**
 *
 * @author Marco Klein
 */
public class RandomWorldBuilder {
    
    private Vector3f worldSize;
    private boolean createGround;
    /**
     * Number of obstacles
     */
    private int density;
    private Vector3f averageObstacleSize = new Vector3f(1.5f, 1.5f, 1.5f);
    private Vector3f obstacleSizeVariance = new Vector3f(1, 1, 1);

    public RandomWorldBuilder setWorldSize(Vector3f worldSize) {
        this.worldSize = worldSize;
        return this;
    }

    public RandomWorldBuilder setCreateGround(boolean createGround) {
        this.createGround = createGround;
        return this;
    }

    public RandomWorldBuilder setDensity(int density) {
        this.density = density;
        return this;
    }

    public RandomWorldBuilder setAverageObstacleSize(Vector3f averageObstacleSize) {
        this.averageObstacleSize = averageObstacleSize;
        return this;
    }

    public RandomWorldBuilder setObstacleSizeVariance(Vector3f obstacleSizeVariance) {
        this.obstacleSizeVariance = obstacleSizeVariance;
        return this;
    }
    
    public Constellation build(World world) {
        Constellation constellation = new Constellation();
        
        constellation.addConstellation(
                new RandomConstellationBuilder()
                .worldSize(worldSize)
                .obstacleSizeRange(averageObstacleSize.subtract(obstacleSizeVariance), averageObstacleSize.add(obstacleSizeVariance))
                .obstacles(density)
                .build(world));
        
        if (createGround) {
            Node ground = new Node("Ground");
            ground.setLocalTranslation(new Vector3f(0, -1, 0));
            GameObjectControl gameObjectControl = new GameObjectControl(world, new GroundModel(new Vector3f(worldSize.x, 1, worldSize.z)), new StaticPhysicsLogic());
            ground.addControl(gameObjectControl);
            constellation.addGameObject(ground);
        }

        return constellation;
    }
    
    
}
