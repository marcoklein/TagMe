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
    
    private float updateTime = 0.0f;
    private float currentUpdateTime = updateTime;
    

    public SyncPlayerControl(Client client) {
        this.client = client;
    }

    @Override
    protected void controlUpdate(float tpf) {
        // TODO check if player has moved
        currentUpdateTime -= tpf;
        if (currentUpdateTime <= 0) {
            currentUpdateTime = updateTime;
            client.send(new UpdateGameObjectPositionMessage(
                    spatial));
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
