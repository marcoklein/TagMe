package network.message.world;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;
import world.control.LogicControl;
import world.control.ModelControl;

/**
 * Initializes a world by calling reset and setting the appropriate world size.
 *
 * @author Marco Klein
 */
@Serializable
public class InitWorldMessage extends WorldMessage {
    
    private Vector3f worldSize;
    
    /**
     * Initial Game Objects.
     */
    private AddGameObjectMessage[] gameObjectMsgs;

    public InitWorldMessage() {
    }

    public InitWorldMessage(Vector3f worldSize, Spatial[] gameObjects) {
        this.worldSize = worldSize;
        gameObjectMsgs = new AddGameObjectMessage[gameObjects.length];
        for (int i = 0; i < gameObjects.length; i++) {
            LogicControl logic = gameObjects[i].getControl(LogicControl.class);
            ModelControl model = gameObjects[i].getControl(ModelControl.class);
            gameObjectMsgs[i] = new AddGameObjectMessage(logic == null ? null : logic.getLogic(), model == null ? null : model.getModel(), (int) gameObjects[i].getUserData("Id"));
        }
    }

    
    @Override
    public void applyToWorld(World world) {
        world.reset();
        // world size
        world.setWorldSize(worldSize);
        // add initial game objects
        for (AddGameObjectMessage msg : gameObjectMsgs) {
            msg.applyToWorld(world);
        }
    }

    
}
