package network.message.world;

import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Node;
import world.World;
import world.GameObjectControl;
import world.gameobject.logic.Logic;
import world.gameobject.model.Model;

/**
 * Adds a game object with an id, a model and a logic to the world.
 *
 * @author Marco Klein
 */
@Serializable
public class AddGameObjectMessage extends GameObjectMessage {
    
    protected Logic logic;
    protected Model model;

    public AddGameObjectMessage() {
    }
    
    public AddGameObjectMessage(GameObjectControl gameObjectControl) {
        id = gameObjectControl.getId();
        logic = gameObjectControl.getLogic();
        model = gameObjectControl.getModel();
    }

    public AddGameObjectMessage(Node gameObject) {
        this(gameObject.getControl(GameObjectControl.class));
    }

    public AddGameObjectMessage(Logic logic, Model model, int id) {
        super(id);
        this.logic = logic;
        this.model = model;
    }

    @Override
    public void applyToGameObject(World world, Node gameObject, GameObjectControl gameObjectControl) {
        gameObject = new Node("GameObject");
        gameObjectControl = new GameObjectControl(world, model, logic);
        gameObject.addControl(gameObjectControl);
        world.addGameObject(gameObject, id);
    }
    
}
