package network.message.world;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Spatial;
import world.World;
import world.factory.GameObjectFactory;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class AddPlayer extends WorldMessage {
    
    private int id;
    private String playerName;
    private Vector3f startLocation;
    private ColorRGBA color;

    public AddPlayer(int id, String playerName, Vector3f startLocation, ColorRGBA color) {
        this.id = id;
        this.playerName = playerName;
        this.startLocation = startLocation;
        this.color = color;
    }

    @Override
    public void applyToWorld(World world) {
        Spatial player = new GameObjectFactory(world).createPlayer(startLocation, color);
        player.setUserData("PlayerName", playerName);
        world.addGameObject(player);
    }
    
}
