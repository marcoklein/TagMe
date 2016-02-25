package world.gameobject.model;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingSphere;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import world.World;

/**
 *
 * @author Marco Klein
 */
@Serializable
public class PlayerModel extends Model<Node> {

    protected ColorRGBA color;
    protected float radius;

    public PlayerModel() {
    }

    public PlayerModel(ColorRGBA color) {
        this.color = color;
        this.radius = 1;
    }
    
    @Override
    public Node createModel(World world) {
        Node player = new Node();
        player.setName("Model");
        Geometry geom = new SphereModel(radius).createModel(world);
        player.setLocalTranslation(0, 1f, 0);
        
        AssetManager assetManager = world.getApp().getAssetManager();
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Diffuse", color);
        mat.setColor("Ambient", color);
        mat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/ColoredTex/Monkey.png"));
        geom.setMaterial(mat);
        
        player.attachChild(geom);
        return player;
    }
    
}
