/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.controls;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Marco Klein
 */
public class ObstacleControl extends AbstractControl {

    @Override
    protected void controlUpdate(float tpf) {
        this.spatial.move(0, 1 * tpf, 0);
        spatial.rotate(0, 10 * tpf, 0);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
