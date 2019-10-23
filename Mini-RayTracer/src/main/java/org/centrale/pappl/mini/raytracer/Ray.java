/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

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
    
}
