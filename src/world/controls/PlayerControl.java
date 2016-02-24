/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.controls;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Marco Klein
 */
public class PlayerControl extends AbstractControl {
    
    private BetterCharacterControl characterControl;

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            // initialize
            // get character control
            characterControl = spatial.getControl(BetterCharacterControl.class);
            if (characterControl == null) {
                throw new UnsupportedOperationException("Player needs a BetterCharacterControl.class.");
            }
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
