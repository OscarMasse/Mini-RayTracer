package org.centrale.pappl.mini.raytracer.scene.object;

import org.centrale.pappl.mini.raytracer.graphics.ShadowRay;
import org.centrale.pappl.mini.raytracer.scene.object.materials.Material;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public abstract class SceneObject {

    //ATTRIBUTES
    
    /**
     * Object's color
     */
    private Vector3 color;
    
    /**
     * Object's Material
     */
    private Material material;

    //GETTERS AND SETTERS
    
    /**
     *
     * @param color
     */
    public void setColor(Vector3 color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Vector3 getColor() {
        return this.color;
    }

    /**
     *
     * @return
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     *
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    
    //OTHER METHODS
    /**
     * Computes ray's intersection with this object
     * @param ray
     * @return
     */
    public abstract RayCastResult intersect(Ray ray);

    /**
     * Computes shadowray's intersection with this object
     * @param shadowRay
     * @return
     */
    public RayCastResult intersect(ShadowRay shadowRay) {
        return intersect(new Ray(shadowRay.getOrigin(), shadowRay.getDirection()));
    }

    /**
     * Computes normal vector for a position on this object
     * @param position
     * @return
     */
    public abstract Vector3 getNormal(Vector3 position);
}
