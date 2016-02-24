/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.controls;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Defines an obstacle of the world.
 * An obstacle is a static Spatial which blocks the player.
 * 
 * Obstacles can appear and change in the world. If an obstacle gets created it will
 * move from under the world (y smaller 0) up to its position (arises from the ground)
 * 
 *
 * @author Marco Klein
 */
public class ObstacleControl extends WorldObjectControl {
    private static final Logger LOG = Logger.getLogger(ObstacleControl.class.getName());
    
    private boolean movingToInitialLocation;
    private float movingToInitialLocationSpeed;
    private Vector3f worldLocation;
    
    private Vector3f tempVector = new Vector3f();

    public ObstacleControl() {
        this(0, new Vector3f());
    }

    /**
     * Creates a new ObstacleControl with given initial speed and target location
     * 
     * @param movingToInitialLocationSpeed how fast obstacle should move to target location. If 0 or smaller spatial will be placed to target location without moving.
     * @param worldLocation target location
     */
    public ObstacleControl(float movingToInitialLocationSpeed, Vector3f worldLocation) {
        this.movingToInitialLocationSpeed = movingToInitialLocationSpeed;
        this.worldLocation = worldLocation;
    }


    @Override
    public void setSpatial(Spatial spatial) {
        if (this.spatial != null) {
            // cleanup (control gets removed from spatial)
        }
        super.setSpatial(spatial);
        if (spatial != null) {
            // initialize
            // let obstacle arise from ground (move it upward from underground)
            if (movingToInitialLocationSpeed <= 0) {
                // do not move if speed is 0 or lower but move object to its target location
                spatial.setLocalTranslation(worldLocation);
                movingToInitialLocation = false;
            } else {
                movingToInitialLocation = true;
            }
        }
        LOG.log(Level.INFO, "Spatial set to {0}", spatial);
    }
    
    

    @Override
    protected void controlUpdate(float tpf) {
        if (movingToInitialLocation) {
            // TODO move towards target location
            // calculate moving vector
            tempVector.set(worldLocation).subtractLocal(spatial.getLocalTranslation()).normalizeLocal().multLocal(movingToInitialLocationSpeed * tpf);
            spatial.move(tempVector);
            // check if obstacle reached its target location
            float distance = tempVector.set(worldLocation).distanceSquared(spatial.getLocalTranslation());
            if (distance < movingToInitialLocationSpeed * tpf) {
                // reached target location
                spatial.getLocalTranslation().set(tempVector);
                movingToInitialLocation = false;
            }
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
