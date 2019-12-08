/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;

/**
 *
 * @author skiara
 */
public class Ray {
    //ATTRIBUTES
    /**
     * Ray's origin
     */
    private Vector3 origin;
    
    /**
     * Ray's direction
     */
    private Vector3 direction;
    
    //CONSTRUCTORS
    /**
     * Constructor
     * @param origin
     * @param direction 
     */
    public Ray(Vector3 origin, Vector3 direction){
        this.origin = origin;
        this.direction = direction.normalized();
    }
    
    /**
     * Default constructor
     */
    public Ray(){
        this.origin = new Vector3();
        this.direction = new Vector3();
    }

    
    //GETTERS AND SETTERS
    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection() {
        return direction;
    }
    
    public Ray generateReflection(Vector3 intersection, Vector3 normal){
        Vector3 reflectedDirection = new Vector3(intersection);
        reflectedDirection.subtract(normal.scale(2).scale(normal.dot(intersection)));
        Ray reflection = new Ray(intersection, reflectedDirection);
        return reflection;
    }
    
    public Ray generateRefraction (){
        
        return new Ray();
    }
    
    public Vector3 getIllumination() {

        Vector3 illumination = new Vector3();

        // Detection of the closest hit object
        SceneObject closestSceneObject = null;
        double shortestDistance = Double.MAX_VALUE;
        RayCastResult rayCastResult;
        RayCastResult hitRayCastResult = null;
        for (SceneObject sceneObject : Scene.getScene().getSceneObjects()) {
            rayCastResult = sceneObject.intersect(this);
            if (rayCastResult.hit) {
                double objectDistance = rayCastResult.intersection.subtract(origin).magnitude();
                if (objectDistance < shortestDistance) {
                    closestSceneObject = sceneObject;
                    shortestDistance = objectDistance;
                    hitRayCastResult = rayCastResult;
                }
            }
        }

        if (closestSceneObject == null) return illumination;

        // Absorption

        //      Ambient light
        illumination = closestSceneObject.getColor().multiply(Scene.getScene().getAmbientLight().getLightColor()).scale(closestSceneObject.getMaterial().getKa());

        //      Shadow Rays
        for (Light light: Scene.getScene().getLights()) {
            Vector3 shadowOrigin = new Vector3(hitRayCastResult.intersection.add(hitRayCastResult.normal.scale(0.1)));

            illumination = illumination.add(new ShadowRay(closestSceneObject, light, shadowOrigin).getIllumination())
//                    .clamp()
                    ;
        }

        // Reflexion

        // Refraction

        return illumination;
    }
}
