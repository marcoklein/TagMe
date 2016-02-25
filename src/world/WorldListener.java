package world;

import com.jme3.scene.Node;

/**
 * Can be added to a World to get notified about everything that happens in the World.
 *
 * @author Marco Klein
 */
public interface WorldListener {
    
    /**
     * Called after the given Game Object has been added to the world.
     * 
     * @param gameObject 
     */
    public void gameObjectAdded(Node gameObject);
    
    /**
     * Called after the given Game Object has been removed of the world.
     * 
     * @param gameObject 
     */
    public void gameObjectRemoved(Node gameObject);
    
}
