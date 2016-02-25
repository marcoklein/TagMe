/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.GameObjectControl;
import world.World;
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
    public void applyToGameObject(World world, Node gameObject, GameObjectControl gameObjectControl) {
        gameObjectControl.setLogic(logic);
    }
    
}
