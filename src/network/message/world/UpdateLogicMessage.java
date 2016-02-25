/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.World;
import world.control.LogicControl;
import world.gameobject.logic.Logic;

/**
 * Updates logic of a GameObject.
 *
 * @author kleimarc
 */
@Serializable
public class UpdateLogicMessage extends GameObjectMessage {
    
    private Logic logic;

    public UpdateLogicMessage() {
    }

    public UpdateLogicMessage(Logic logic, int id) {
        super(id);
        this.logic = logic;
    }
    
    @Override
    public void applyToGameObject(World world, Spatial gameObject) {
        LogicControl logicControl = gameObject.getControl(LogicControl.class);
        if (logicControl == null) {
            logicControl = new LogicControl(world, logic);
            gameObject.addControl(logicControl);
        } else {
            logicControl.setLogic(logic);
        }
    }
    
}
