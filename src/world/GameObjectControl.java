package world;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import world.gameobject.logic.Logic;
import world.gameobject.model.Model;

/**
 * Control every GameObject which is added to a world must have.
 * A game object may have a graphical representation (a Model) and a logical representation
 * (a Logic).
 *
 * @author Marco Klein
 */
public class GameObjectControl extends AbstractControl {

    protected World world;
    
    protected Node gameObject;
    
    protected Model model;
    protected Spatial modelNode;
    
    protected Logic logic;
    
    private int id;
    

    public GameObjectControl(World world) {
        this.world = world;
    }

    public GameObjectControl(World world, Model model, Logic logic) {
        this.world = world;
        this.model = model;
        this.logic = logic;
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    public void setSpatial(Spatial spatial) {
        // every game object must be a node
        if (spatial != null && !(spatial instanceof Node)) {
            throw new RuntimeException("A GameObject must be a Node!");
        }
        this.gameObject = (Node) spatial;
        
        super.setSpatial(spatial);
        
        // apply model and logic
        setModel(model);
        if (gameObject != null && logic != null) {
            logic.addLogic(world, gameObject);
        }
        
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        if (gameObject != null && model != null) {
            // attach model to model node
            if (modelNode != null) {
                // remove model node if existing
                modelNode.removeFromParent();
            }
            modelNode = model.createModel(world);
            modelNode.setName("Model");
            
            gameObject.attachChild(modelNode);
        }
    }

    public Spatial getModelNode() {
        return modelNode;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        if (this.logic != null) {
            // remove preexisting logic
            this.logic.removeLogic(world, gameObject);
        }
        this.logic = logic;
        if (gameObject != null && logic != null) {
            logic.addLogic(world, gameObject);
        }
    }

    public World getWorld() {
        return world;
    }

    public Node getGameObject() {
        return gameObject;
    }

    public int getId() {
        return id;
    }
    
    /**
     * Called by world as the GameObjects gets added to a world.
     * 
     * @param id
     * @return 
     */
    void setId(int id) {
        this.id = id;
    }
    
}
