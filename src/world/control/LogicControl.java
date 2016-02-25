package world.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import world.gameobject.logic.Logic;

/**
 * Used to store an associated Model with a GameObject.
 * 
 *
 * @author Marco Klein
 */
public class LogicControl extends AbstractControl {
    private Logic logic;

    public LogicControl(Logic logic) {
        this.logic = logic;
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }
    
}
