/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.RayTracer;
import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Ray class to create rays
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Ray {
    // ATTRIBUTES
    /**
     * Ray's origin
     */
    private Vector3 origin;
    
    /**
     * Ray's direction
     */
    private Vector3 direction;

    private List<Ray> previousRays;
    
    // CONSTRUCTORS
    /**
     * Constructor
     * @param origin
     * @param direction 
     */
    public Ray(Vector3 origin, Vector3 direction){
        this.origin = origin;
        this.direction = direction.normalized();
        this.previousRays = new ArrayList<>();
    }

    public Ray(Vector3 origin, Vector3 direction, Ray previousRay) {
        this.origin = origin;
        this.direction = direction.normalized();
        this.previousRays = new ArrayList<>();
        for (Ray ray : previousRay.getPreviousRays()) {
            this.previousRays.add(ray);
        }
        this.previousRays.add(previousRay);
    }
    
    /**
     * Default constructor
     */
    public Ray(){
        this.origin = new Vector3();
        this.direction = new Vector3();
        this.previousRays = new ArrayList<>();
    }

    
    //GETTERS AND SETTERS

    /**
     *
     * @return
     */
    public Vector3 getOrigin() {
        return origin;
    }

    /**
     *
     * @return
     */
    public Vector3 getDirection() {
        return direction;
    }
    
    /**
     * Generates the reflected ray from an intersection point and a normal
     * @param intersection
     * @param normal
     * @return
     */
    public Ray generateReflection(Vector3 intersection, Vector3 normal){
        Vector3 reflectedDirection = new Vector3(intersection);
        reflectedDirection.subtract(normal.scale(2).scale(normal.dot(intersection)));
        Ray reflection = new Ray(intersection, reflectedDirection);
        return reflection;
    }

    public int getDepth() {
        return previousRays.size();
    }

    public List<Ray> getPreviousRays() {
        return previousRays;
    }
    
    /**
     *
     * @return
     */
    /**
    public Ray generateRefraction (){
        
        return new Ray();
    }
    */
    
    /**
     * Calculates the illumination of the point hit by this ray
     * @return
     */
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

        Vector3 intersection = hitRayCastResult.intersection;
        Vector3 normal = hitRayCastResult.normal;

        // Absorption
            // Ambient light
        illumination = closestSceneObject.getColor().multiply(Scene.getScene().getAmbientLight().getLightColor()).scale(closestSceneObject.getMaterial().getKa());

            // Shadow Rays
        for (Light light: Scene.getScene().getLights()) {
            Vector3 shadowOrigin = new Vector3(intersection).add(normal.scale(RayTracer.EPSILON));
            ShadowRay shadowRay = new ShadowRay(closestSceneObject, light, this, shadowOrigin);

            if (shadowRay.getDirection().dot(normal) >= 0) {
                illumination = illumination.add(shadowRay.getIllumination())
                        .clamp()
                ;
            }
        }
        illumination = illumination.scale(closestSceneObject.getMaterial().getAbsorbance());

        // Reflexion
        if (closestSceneObject.getMaterial().getReflectance() != 0) { //TODO: Condition on reflection coefficient of the material
//            System.out.println("" + getDepth());
            if(getDepth() < RayTracer.maxDepth) {
                Vector3 reflectedRayOrigin = intersection.add(normal.scale(RayTracer.EPSILON));
                // R = I−2(N⋅I)N
                Vector3 reflectedRayDirection = new Vector3(intersection.normalized()).subtract(normal.scale(2 * normal.dot(intersection.normalized())));

                Ray reflectedRay = new Ray(reflectedRayOrigin, reflectedRayDirection, this);
                illumination = illumination.add(reflectedRay.getIllumination().scale(closestSceneObject.getMaterial().getReflectance()))
                        .clamp()
                ;
            } else return new Vector3();
        }

        // Refraction

        return illumination;
    }
}
