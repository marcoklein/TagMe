/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.message.world;

import com.jme3.scene.Node;
import world.World;
import world.GameObjectControl;

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
        Node gameObject = world.getGameObject(id);
        applyToGameObject(world, gameObject, gameObject.getControl(GameObjectControl.class));
    }
    
    public abstract void applyToGameObject(World world, Node gameObject, GameObjectControl gameObjectControl);
    
    
}
