/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;
import world.control.ModelControl;
import world.gameobject.model.Model;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class UpdateModelMessage extends GameObjectMessage {
    
    private Model model;

    public UpdateModelMessage() {
    }

    public UpdateModelMessage(Model model, int id) {
        super(id);
        this.model = model;
    }
    
    @Override
    public void applyToGameObject(World world, Spatial gameObject) {
        gameObject.getControl(ModelControl.class).setModel(model);
    }
    
}
