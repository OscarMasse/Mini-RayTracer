package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;

/**
 * RayCastResult class
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class RayCastResult {
    //ATTRIBUTES
    /**
     * Intersection point
     */
    public Vector3 intersection;

    /**
     * Distance to the object
     */
    public double distance;

    /**
     * Object which was hit
     */
    public SceneObject hitObject;

    /**
     * Normal at intersection point
     */
    public Vector3 normal;

    /**
     * Whether an object was hit or not
     */
    public boolean hit;

    //CONSTRUCTORS
    /**
     *
     */
    public RayCastResult(){
        this.distance = Double.MAX_VALUE;
        this.hit = false;
    }

    /**
     *
     * @param intersection
     * @param hitObject
     */
    public RayCastResult(Vector3 intersection, SceneObject hitObject) {
        this.intersection = intersection;
        this.hitObject = hitObject;
        this.normal = hitObject.getNormal(intersection);
    }

    //GETTERS AND SETTERS
    /**
     *
     * @param intersection
     * @param hitObject
     */
    public void setResult(Vector3 intersection, SceneObject hitObject) {
        this.intersection = intersection;
        this.hitObject = hitObject;
        this.normal = hitObject.getNormal(intersection);
    }
}
