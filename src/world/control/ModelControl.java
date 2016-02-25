package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import world.gameobject.model.Model;

/**
 *
 * @author Marco Klein
 */
public class ModelControl extends AbstractControl {

    private Model model;

    public ModelControl() {
    }

    public ModelControl(Model model) {
        this.model = model;
    }
    
    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
}
