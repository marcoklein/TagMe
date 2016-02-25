package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import world.gameobject.logic.Logic;
import world.gameobject.model.Model;

/**
 * DO NOT USE THIS CLASS
 * 
 * Control every GameObject which is added to a world must have.
 * A game object may have a graphical representation (a Model) and a logical representation
 * (a Logic).
 *
 * @author Marco Klein
 */
public class GameObjectControl extends AbstractControl {

    private Model model;
    private Logic logic;

    public GameObjectControl() {
    }

    public GameObjectControl(Model model) {
        this.model = model;
    }
    
    public GameObjectControl(Model model, Logic logic) {
        this.model = model;
        this.logic = logic;
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
        if (spatial != null) {
            if (model != null) {
                spatial.addControl(new ModelControl(model));
            }
            if (logic != null) {
                spatial.addControl(new LogicControl(logic));
            }
        }
    }
    
    
    
}
