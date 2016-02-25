package network.gamemode;

import world.GameObjectControl;

/**
 *
 * @author Marco Klein
 */
public class TagGameMode extends GameMode {


    @Override
    public void playerJoined(GameObjectControl player) {
        System.out.println("Player joined");
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
