package world;

import com.jme3.scene.Spatial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Constellations consist out of several obstacles and normally form a big "thing"
 * for example a house.
 * 
 * Constellations are used to make worlds more interesting.
 *
 * @author Marco Klein
 */
public class Constellation implements Serializable {
    
    private ArrayList<Spatial> obstacles = new ArrayList<>();
    
    public void addObstacle(Spatial obstacle) {
        obstacles.add(obstacle);
    }
    
    public void removeObstacle(Spatial obstacle) {
        obstacles.remove(obstacle);
    }

    public ArrayList<Spatial> getObstacles() {
        return obstacles;
    }
    
}
