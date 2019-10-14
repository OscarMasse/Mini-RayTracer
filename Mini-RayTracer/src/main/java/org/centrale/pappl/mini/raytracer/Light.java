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
public class Light {
    
    //ATTRIBUTES
    
    /**
     * Light's origin point
     */
    private Vector3 origin;
    
    /**
     * Light's intensity
     */
    private int intensity;
    
    //CONSTRUCTORS
    
    /**
     * Constructor
     * @param origin
     * @param intensity 
     */
    public Light (Vector3 origin, int intensity){
        this.origin = origin;
        this.intensity = intensity;
    }
    
    /**
     * Default constructor
     */
    public Light(){
        this.origin = new Vector3();
        this.intensity = 0;
    }
    
    //GETTERS AND SETTERS

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
    
}
