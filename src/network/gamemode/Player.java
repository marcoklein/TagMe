package network.gamemode;

import com.jme3.network.Server;
import com.jme3.scene.Spatial;

/**
 * Facade to set data of a player which get automatically synchronized to all clients.
 *
 * @author Marco Klein
 */
public class Player {
    
    private Server server;
    private Spatial spatial;
    
    private String name;
    private int id;
    
    private int points;
    
    private boolean catcher;

    public Player(Server server, Spatial spatial) {
        this.server = server;
        this.spatial = spatial;
        name = spatial.getUserData("PlayerName");
        id = spatial.getUserData("PlayerId");
    }

    public Server getServer() {
        return server;
    }

    public Spatial getSpatial() {
        return spatial;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}
