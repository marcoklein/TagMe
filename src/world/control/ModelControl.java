package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import world.World;
import world.gameobject.model.Model;

/**
 *
 * @author Marco Klein
 */
public class ModelControl extends AbstractControl {

    private World world;
    private Model model;

    public ModelControl() {
    }

    public ModelControl(World world, Model model) {
        this.world = world;
        this.model = model;
    }

    
    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null && model != null) {
            ((Node) spatial).detachChildNamed("Model");
            ((Node) spatial).attachChild(model.createModel(world));
        }
    }
    
    

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        if (spatial != null && model != null) {
            ((Node) spatial).detachChildNamed("Model");
            ((Node) spatial).attachChild(model.createModel(world));
        }
    }
    
}
