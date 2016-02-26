package world.factory;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import world.Constellation;
import world.World;
import world.builder.ObstacleBuilder;

/**
 *
 * @author Marco Klein
 */
public class ObstacleConstellationBuilder {
    
    private ArrayList<Vector3f> locations = new ArrayList<>();
    private ArrayList<Vector3f> sizes = new ArrayList<>();

    public ObstacleConstellationBuilder() {
    }
    
    public ObstacleConstellationBuilder add(Vector3f location, Vector3f size) {
        locations.add(location);
        sizes.add(size);
        return this;
    }
    
    public Constellation build(World world) {
        Constellation constellation = new Constellation();
        
        for (int i = 0; i < locations.size(); i++) {
            constellation.addGameObject(
                    new ObstacleBuilder()
                    .location(locations.get(i))
                    .size(sizes.get(i))
                    .build(world));
        }
        
        
        return constellation;
    }
    
    
}
