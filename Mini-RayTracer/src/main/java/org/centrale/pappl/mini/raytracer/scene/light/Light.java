/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public abstract class Light {
    
    //ATTRIBUTES
    
    /**
     * Light's color
     */
    private Vector3 lightColor;
    
    /**
     * Light's intensity
     */
    private double intensity;
    
    /**
     * Diffuse light intensity
     */
    private Vector3 id;
    
    /**
     * Specular light intensity
     */
    private Vector3 is;

    //CONSTRUCTORS
    /**
     *
     * @param lightColor
     * @param intensity
     */
    protected Light(Vector3 lightColor, double intensity) {
        this.lightColor = lightColor;
        this.intensity = intensity;
        this.id = new Vector3(1, 1, 1);
        this.is = new Vector3(1, 1, 1);
    }

    //GETTERS AND SETTERS
    /**
     *
     * @return
     */
    public Vector3 getLightColor() {
        return lightColor;
    }

    /**
     *
     * @return
     */
    public double getIntensity(){
        return intensity;
    }
    
    /**
     *
     * @param newIntensity
     */
    public void setIntensity(double newIntensity){
        this.intensity = newIntensity;
    }

    /**
     *
     * @return
     */
    public Vector3 getId() {
        return this.is;
    }

    /**
     *
     * @return
     */
    public Vector3 getIs() {
        return this.is;
    }
}