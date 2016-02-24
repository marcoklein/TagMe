package world;

import com.jme3.scene.Node;
import world.controls.PlayerControl;
import world.controls.ObstacleControl;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.util.ArrayList;

/**
 * Holds all GameObjects.
 * If a Spatial is added the Controls of the spatials are checked and the spatial
 * gets added accordingly to ids controls.
 *
 * @author Marco Klein
 */
public class World {
    
    private ArrayList<Spatial> players;
    
    private ArrayList<Spatial> obstacles;
    
    private Node rootNode;

    public World(Node rootNode) {
        this.rootNode = rootNode;
        initialize();
    }
    
    public void initialize() {
        players = new ArrayList<Spatial>();
        obstacles = new ArrayList<Spatial>();
    }
    
    
    
    /**
     * Adds the given game object (in form of a Spatial) to the world.
     * The game object type is determined by the spatial classes.
     * 
     * @param entity 
     */
    public void addSpatial(Spatial entity) {
        Control control = entity.getControl(PlayerControl.class);
        if (control != null) {
            // add player with PlacerControl
            players.add(entity);
        } else {
            control = entity.getControl(ObstacleControl.class);
            if (control != null) {
                // add obstacle with ObstacleControl
                obstacles.add(entity);
            }
        }
    }
    
}
