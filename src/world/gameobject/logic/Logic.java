/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.gameobject.logic;

import com.jme3.scene.Node;
import world.World;

/**
 * Adds logic to a GameObject.
 *
 * @author Marco Klein
 */
public abstract class Logic {
    
    public abstract void addLogic(World world, Node gameObject);
    /**
     * Remove the logic added in addLogic().
     * The GameObject must have the same state as before logic was added.
     * 
     * @param world
     * @param gameObject 
     */
    public abstract void removeLogic(World world, Node gameObject);
}
