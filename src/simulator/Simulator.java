package simulator;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import world.World;

/**
 * Simulates all Game Objects of the world.
 * 
 * It moves obtacles and players and simulates.
 * 
 * There is only collision detection between players and obstacles.
 * So players can walk over each other.
 * For obstacles and obstacles collision detection would make no sense since
 * they are part of the world.
 * 
 * The Simulator uses the WorldListener to know when new objects are added.
 * It checks added controls to determin which kind of Game Object has been added.
 * For example if it finds a PlayerControl it assuems the Game Object to be a player.
 *
 * @author Marco Klein
 */
public class Simulator extends AbstractAppState {
    private World world;

    public Simulator(World world) {
        this.world = world;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
    
    
}
