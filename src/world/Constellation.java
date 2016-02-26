package world;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
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
    
    private Vector3f translation = new Vector3f();
    private Quaternion rotation = new Quaternion();
    
    private ArrayList<Node> gameObjects = new ArrayList<>();
    
    public void setTranslation(Vector3f translation) {
        Vector3f change = translation.subtract(this.translation);
        for (Node gameObject : gameObjects) {
            gameObject.getLocalTranslation().addLocal(change);
        }
        this.translation = translation;
    }
    
    public void setRotation(Quaternion rotation) {
        Quaternion change = rotation.subtract(this.rotation);
        for (Node gameObject : gameObjects) {
            gameObject.getLocalRotation().addLocal(change);
        }
        this.rotation = rotation;
    }
    
    public void addConstellation(Constellation other) {
        gameObjects.addAll(other.gameObjects);
    }
    
    public void addGameObject(Node gameObject) {
        gameObjects.add(gameObject);
    }
    
    public void removeGameObject(Node gameObject) {
        gameObjects.remove(gameObject);
    }

    public ArrayList<Node> getGameObjects() {
        return gameObjects;
    }
    
}
