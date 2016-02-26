package network.gamemode;

import com.jme3.math.Vector3f;
import network.message.world.SetGameObjectLocationMessage;
import network.message.world.UpdateGameObjectPositionMessage;
import world.GameObjectControl;
import world.gameobject.logic.AttachLogic;
import world.gameobject.model.AssetModel;

/**
 * Default game rules - there is always one catcher.
 *
 * @author Marco Klein
 */
public class TagGameMode extends GameMode {

    private Vector3f spawnPosition = new Vector3f(50, 50, 50);

    @Override
    public void playerJoined(GameObjectControl player) {
        GameObjectControl gameObjectControl = new GameObjectControl(world, new AssetModel("Effects/catcherEffect.j3o"), new AttachLogic(player.getId()));
        world.addGameObject(gameObjectControl);
    }

    @Override
    public void playerLeft(GameObjectControl player) {
    }

    @Override
    public void playerCollision(GameObjectControl playerA, GameObjectControl playerB) {
    }

    @Override
    public void playerLeftWorldBoundaries(GameObjectControl player) {
        UpdateGameObjectPositionMessage updatePos = new UpdateGameObjectPositionMessage(null, spawnPosition, player.getId());
        updatePos.setReliable(true);
        server.broadcast(new SetGameObjectLocationMessage(spawnPosition, player.getId()));
        System.out.println("Player left world boundaries.");
    }

    
    
}
