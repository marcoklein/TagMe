package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import world.World;
import world.gameobject.logic.Logic;

/**
 * Used to store an associated Model with a GameObject.
 * 
 *
 * @author Marco Klein
 */
public class LogicControl extends AbstractControl {
    private World world;
    private Logic logic;

    public LogicControl() {
    }

    public LogicControl(World world, Logic logic) {
        this.world = world;
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
        if (spatial != null && logic != null) {
            logic.applyLogic(world, (Node) spatial);
        }
    }
    
    
    
    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
        if (spatial != null && logic != null) {
            logic.applyLogic(world, (Node) spatial);
        }
    }
    
}
