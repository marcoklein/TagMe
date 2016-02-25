package network.client;

import com.jme3.network.Client;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import network.message.world.UpdateGameObjectPositionMessage;

/**
 * Keeps track of the players location and rotation and sends changes to the server.
 *
 * @author Marco Klein
 */
public class SyncPlayerControl extends AbstractControl {
    
    private Client client;
    
    

    public SyncPlayerControl(Client client) {
        this.client = client;
    }

    @Override
    protected void controlUpdate(float tpf) {
        // TODO check if player has moved
        client.send(new UpdateGameObjectPositionMessage(
                spatial
                ));
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
