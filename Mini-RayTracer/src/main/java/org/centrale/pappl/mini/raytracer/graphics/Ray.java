/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.graphics;

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
    
    
}
