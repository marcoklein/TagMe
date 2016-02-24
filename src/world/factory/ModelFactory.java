/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.factory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

/**
 * Creates Geometries which are the visible part of a Spatial.
 *
 * @author Marco Klein
 */
public class ModelFactory {
    
    /**
     * Creates a new box using the given dimensions.
     * 
     * @param width
     * @param height
     * @param depth
     * @return 
     */
    public static Geometry createBox(AssetManager assetManager, float width, float height, float depth, ColorRGBA color) {
        Box b = new Box(width, height, depth);
        Geometry geom = new Geometry("Box", b);
        return geom;
    }
    
    /**
     * Creates a sphere with the given radius.
     * 
     * @param radius
     * @return 
     */
    public static Geometry createSphere(AssetManager assetManager, float radius, ColorRGBA color) {
        Sphere sphere = new Sphere(9, 16, radius);
        Geometry geom = new Geometry("Sphere", sphere);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Diffuse", color);
        mat.setColor("Ambient", color);
        mat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/ColoredTex/Monkey.png"));
        geom.setMaterial(mat);
        return geom;
    }
    
}
