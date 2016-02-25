/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.scene.Spatial;
import world.World;

/**
 *
 * @author Marco Klein
 */
public abstract class GameObjectMessage extends WorldMessage {

    protected int id;

    public GameObjectMessage() {
    }

    public GameObjectMessage(boolean reliable) {
        super(reliable);
    }

    public GameObjectMessage(int id) {
        this.id = id;
    }

    public GameObjectMessage(int id, boolean reliable) {
        super(reliable);
        this.id = id;
    }
    

    
    @Override
    public void applyToWorld(World world) {
        applyToGameObject(world, world.getGameObject(id));
    }
    
    public abstract void applyToGameObject(World world, Spatial gameObject);
    
    
}
