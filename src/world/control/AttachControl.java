package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Marco Klein
 */
public class AttachControl extends AbstractControl {
    
    private Node gameObject;

    public AttachControl() {
    }

    public AttachControl(Node gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (gameObject != null) {
            spatial.setLocalTranslation(gameObject.getLocalTranslation());
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    
}
