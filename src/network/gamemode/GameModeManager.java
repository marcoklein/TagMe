package network.gamemode;

import com.jme3.app.state.AbstractAppState;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.network.Server;
import java.util.ArrayList;
import world.GameObjectControl;
import world.World;

/**
 * Manages all GameModes.
 *
 * @author Marco Klein
 */
public class GameModeManager extends AbstractAppState {
    
    private Server server;
    private World world;
    
    /**
     * Active GameMode.
     */
    private GameMode gameMode;;
    
    private ArrayList<GameObjectControl> players = new ArrayList<>();

    public GameModeManager(Server server, World world) {
        this.server = server;
        this.world = world;
    }

    @Override
    public void update(float tpf) {
        // test for collisions
        CollisionResults result = new CollisionResults();
        GameObjectControl playerA;
        GameObjectControl playerB;
        
        for (int i = 0; i < players.size(); i++) {
            playerA = players.get(i);
            for (int j = i + 1; j < players.size(); j++) {
                playerB = players.get(j);
                if (playerA.getGameObject().getLocalTranslation().subtract(playerB.getGameObject().getLocalTranslation()).length() < 2) {
                    gameMode.playerCollision(playerA, playerB);
                }
//                result.clear();
//                if (playerA.getGameObject().collideWith(playerB.getGameObject(), result) > 0) {
//                    // collision
//                    gameMode.playerCollision(playerA, playerB);
//                }
                
            }
        }
    }
    
    public void changeGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        gameMode.setManager(this);
    }
    
    public void addPlayer(GameObjectControl player) {
        // add collision shape
        player.getGameObject().setModelBound(new BoundingSphere(1, new Vector3f(0, 1, 0)));
        player.getGameObject().updateModelBound();
        
        players.add(player);
        if (gameMode != null) {
            gameMode.playerJoined(player);
        }
    }
    
    public void removePlayer(GameObjectControl player) {
        players.remove(player);
        if (gameMode != null) {
            gameMode.playerLeft(player);
        }
    }

    public Server getServer() {
        return server;
    }

    public World getWorld() {
        return world;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<GameObjectControl> getPlayers() {
        return players;
    }
    
}
