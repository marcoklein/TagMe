package network.gamemode;

import com.jme3.network.Server;
import world.GameObjectControl;
import world.World;

/**
 * Marco Klein
 *
 * @author Marco Klein
 */
public abstract class GameMode {
    
    protected Server server;
    protected World world;
    
    protected GameModeManager manager;

    public abstract void initialize(GameModeManager manager);
    
    public abstract void update(float tpf);
    
    public abstract void playerJoined(GameObjectControl player);
    public abstract void playerLeft(GameObjectControl player);
    
    public abstract void playerCollision(GameObjectControl playerA, GameObjectControl playerB);
    
    public abstract void playerLeftWorldBoundaries(GameObjectControl player);
    
    
    
    
    void setManager(GameModeManager manager) {
        this.manager = manager;
        this.server = manager.getServer();
        this.world = manager.getWorld();
        initialize(manager);
    }
}
