package world;

import com.jme3.scene.Node;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Constellations consist out of several Game Objects which normally form a big "thing"
 * for example a house.
 * 
 * Constellations are used to make worlds more interesting.
 *
 * @author Marco Klein
 */
public class Constellation implements Serializable {
    
    private ArrayList<Node> gameObjects = new ArrayList<>();
    
    public void addGameObject(Node obstacle) {
        gameObjects.add(obstacle);
    }
    
    public void removeGameObject(Node obstacle) {
        gameObjects.remove(obstacle);
    }

    public ArrayList<Node> getGameObjects() {
        return gameObjects;
    }
    
}
