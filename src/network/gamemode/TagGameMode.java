package network.gamemode;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import network.message.world.SetGameObjectLocationMessage;
import network.message.world.UpdateGameObjectPositionMessage;
import world.GameObjectControl;
import world.builder.RandomWorldBuilder;
import world.gameobject.logic.AttachLogic;
import world.gameobject.model.AssetModel;

/**
 * Default game rules - there is always one catcher.
 *
 * @author Marco Klein
 */
public class TagGameMode extends GameMode {

    private Vector3f spawnPosition = new Vector3f(25, 50, 25);
    
    private GameObjectControl currentCatcherEffect;
    private GameObjectControl currentCatcher;
    
    private float catchCooldown = 5;
    private float currentCatchCooldown = catchCooldown;
    
    private ArrayList<GameObjectControl> players;

    @Override
    public void initialize(GameModeManager manager) {
        players = manager.getPlayers();
        
//        // set up world
//        world.addConstellation(new RandomConstellationBuilder()
//                .worldSize(new Vector3f(50, 50, 50))
//                .obstacles(200)
//                .obstacleSizeRange(new Vector3f(0.5f, 0.5f, 0.5f), new Vector3f(4f, 4f, 4f))
//                .build(world)
//                );
//        // add ground
//        world.addGameObject(new ObstacleBuilder()
//                .size(new Vector3f(25, 1, 25))
//                .build(world)
//                );
        // set up world
        world.addConstellation(
                new RandomWorldBuilder()
                .setWorldSize(new Vector3f(20, 5, 20))
                .setCreateGround(true)
                .setDensity(200)
                .build(world));
    }

    @Override
    public void update(float tpf) {
        currentCatchCooldown -= tpf;
    }

    @Override
    public void playerJoined(GameObjectControl player) {
        if (players.size() == 1) {
            // first player
        } else {
            // at least 2 players - let new player be the catcher
            changeCatcher(player);
        }
    }

    @Override
    public void playerLeft(GameObjectControl player) {
        if (players.size() > 1) {
            // if player left let last player be the catcher
            changeCatcher(players.get(players.size() - 1));
        }
    }

    @Override
    public void playerCollision(GameObjectControl playerA, GameObjectControl playerB) {
        if (playerA == currentCatcher) {
            changeCatcher(playerB);
        } else if (playerB == currentCatcher) {
            changeCatcher(playerA);
        }
    }

    @Override
    public void playerLeftWorldBoundaries(GameObjectControl player) {
        UpdateGameObjectPositionMessage updatePos = new UpdateGameObjectPositionMessage(null, spawnPosition, player.getId());
        updatePos.setReliable(true);
        server.broadcast(new SetGameObjectLocationMessage(spawnPosition, player.getId()));
        System.out.println("Player left world boundaries.");
    }
    
    public void changeCatcher(GameObjectControl catcher) {
        if (currentCatchCooldown > 0) {
            return; // cant change catcher
        }
        System.out.println("Changed catcher");
        currentCatchCooldown = catchCooldown;
        if (currentCatcherEffect != null) {
            // there is already a catcher - remove catcher effect
            world.removeGameObject(currentCatcherEffect);
        }
        if (catcher != null) {
            currentCatcherEffect = createCatcherEffect(catcher);
            world.addGameObject(currentCatcherEffect);
        }
        currentCatcher = catcher;
    }
    
    public GameObjectControl createCatcherEffect(GameObjectControl catcher) {
        return new GameObjectControl(world, new AssetModel("Effects/catcherEffect.j3o"), new AttachLogic(catcher.getId()));
    }

    
    
}
