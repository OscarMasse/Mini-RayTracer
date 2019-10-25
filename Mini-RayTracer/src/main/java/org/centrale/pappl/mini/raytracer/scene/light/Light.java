/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author skiara
 */
public class Light {
    
    //ATTRIBUTES
    
    /**
     * Light's direction
     */
    private Vector3 direction;
    
    
    /**
     * Light's color
     */
    private Vector3 lightColor;
    
    //CONSTRUCTORS
    
    /**
     * Constructor
     * @param direction
     * @param lightColor 
     */
    public Light (Vector3 direction, Vector3 lightColor){
        this.direction = direction;
        this.lightColor = lightColor;
    }
    
    /**
     * Default constructor
     */
    public Light(){
        this.direction = new Vector3();
        this.lightColor = new Vector3();
    }
    
    //GETTERS AND SETTERS

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getLightColor() {
        return lightColor;
    }

    public void setLightColor(Vector3 lightColor) {
        this.lightColor = lightColor;
    }

}
