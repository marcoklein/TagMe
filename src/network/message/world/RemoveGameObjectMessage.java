/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;

/**
 * Remvoves Game Object with given id.
 *
 * @author Marco Klein
 */
@Serializable
public class RemoveGameObjectMessage extends GameObjectMessage {

    public RemoveGameObjectMessage() {
    }

    public RemoveGameObjectMessage(int id) {
        super(id);
    }

    @Override
    public void applyToGameObject(World world, Spatial gameObject) {
        world.removeGameObject(id);
    }
    
}
