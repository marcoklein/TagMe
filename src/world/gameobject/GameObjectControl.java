package world.gameobject;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import world.World;
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
    protected Logic logic;
    
    /**
     * List of game objects which are attached to this game object.
     */
    protected ArrayList<Node> attachedGameObjects;

    public GameObjectControl() {
    }

    public GameObjectControl(Model model) {
        this.model = model;
    }
    
    public GameObjectControl(Model model, Logic logic) {
        this.model = model;
        this.logic = logic;
    }
    
    public void attachGameObject(Node gameObject) {
        attachedGameObjects.add(gameObject);
    }
    
    public void detachGameObject(int id) {
        detachGameObject(world.getGameObject(id));
    }
    
    public void detachGameObject(Node gameObject) {
        
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
        setLogic(logic);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        if (gameObject != null && model != null) {
            gameObject.detachChildNamed("Model");
            // attach model to model node
            Node modelNode = new Node("Model");
            modelNode.attachChild(model.createModel(world));
            
            gameObject.attachChild(modelNode);
        }
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
        if (gameObject != null && logic != null) {
            logic.addLogic(world, gameObject);
        }
    }
    
}
