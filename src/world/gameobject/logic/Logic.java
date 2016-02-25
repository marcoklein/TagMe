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
    
    public abstract void applyLogic(World world, Node gameObject);
}
