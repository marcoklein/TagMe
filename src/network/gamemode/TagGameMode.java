package network.gamemode;

import world.GameObjectControl;
import world.gameobject.logic.AttachLogic;
import world.gameobject.model.AssetModel;

/**
 *
 * @author Marco Klein
 */
public class TagGameMode extends GameMode {


    @Override
    public void playerJoined(GameObjectControl player) {
        System.out.println("Player joined");
        GameObjectControl gameObjectControl = new GameObjectControl(world, new AssetModel("Effects/catcherEffect.j3o"), new AttachLogic(player.getId()));
        world.addGameObject(gameObjectControl);
    }

    @Override
    public void playerLeft(GameObjectControl player) {
        System.out.println("Player left");
    }

    @Override
    public void playerCollision(GameObjectControl playerA, GameObjectControl playerB) {
        System.out.println("Collision");
    }

    
    
}
