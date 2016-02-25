/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.gameobject.model;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import world.World;

/**
 *
 * @author Marco Klein
 */
public class SphereModel extends Model<Geometry> {

    protected float radius;

    public SphereModel() {
    }

    public SphereModel(float radius) {
        this.radius = radius;
    }
    
    @Override
    public Geometry createModel(World world) {
        Sphere sphere = new Sphere(9, 16, radius);
        Geometry geom = new Geometry("Sphere", sphere);
        return geom;
    }
    
}
