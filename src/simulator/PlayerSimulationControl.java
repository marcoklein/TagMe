package simulator;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Marco Klein
 */
public class PlayerSimulationControl extends AbstractControl {

    private Vector3f walkDirection = new Vector3f();
    
    private boolean onGround;
    
    /**
     * If true the player wants to jump.
     */
    private boolean performJump;
    /**
     * If true the player is jumping.
     */
    private boolean jumping;


    private Vector3f tempVector = new Vector3f();
    
    @Override
    protected void controlUpdate(float tpf) {
        
        
        // move the player into walking direction
        tempVector.set(walkDirection).multLocal(tpf);
        spatial.getLocalTranslation().addLocal(tempVector);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    
    
    
    
    public void jump() {
        performJump = true;
    }
    
    public Vector3f getWalkDirection() {
        return walkDirection;
    }
    
    public void setWalkDirection(Vector3f walkDirection) {
        this.walkDirection = walkDirection;
    }
    
    public boolean isInAir() {
        return !onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    
}
