/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.gameobject.logic;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.World;
import world.control.PlayerControl;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class PlayerLogic extends Logic {
    private static final Logger LOG = Logger.getLogger(PlayerLogic.class.getName());
    
    private Vector3f startLocation;

    public PlayerLogic() {
    }

    public PlayerLogic(Vector3f startLocation) {
        this.startLocation = startLocation;
    }
    
    @Override
    public void addLogic(World world, Node player) {
        Spatial model = player.getChild("Model");
        if (model == null) {
            LOG.warning("Model is null - can not apply PlayerLogic.");
        }
        LOG.info("Applying Player logic.");
        
        // move player to start location
        if (startLocation != null) {
            player.setLocalTranslation(startLocation);
            LOG.log(Level.INFO, "Player starting at {0}", startLocation);
        }
        
        // add controls
        BetterCharacterControl characterControl = new BetterCharacterControl(1f, 2f, 1f);
        characterControl.setJumpForce(new Vector3f(0, 11f, 0));
        player.addControl(characterControl);
        player.addControl(new PlayerControl(world, 8));
        
        // attach camera
        ChaseCamera cam = new ChaseCamera(world.getApp().getCamera(), model, world.getApp().getInputManager());
        cam.setDragToRotate(false);
        cam.setInvertVerticalAxis(true);
        
        // add physics
        world.getBulletAppState().getPhysicsSpace().addAll(player);
    }

    @Override
    public void removeLogic(World world, Node gameObject) {
        // TODO remove player logic
        throw new UnsupportedOperationException("You should not remove a Player logic. (Unsupported)");
    }
    
}
