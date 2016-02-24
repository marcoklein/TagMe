/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.controls;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 * Added to game objects which are destroyable.
 *
 * @author Marco Klein
 */
public class DestroyableControl extends AbstractControl {
    
    /**
     * Destroys the given spatial by removing it from its parent (with some
     * cool animation?)
     */
    public void destroy() {
        
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    
    
}
