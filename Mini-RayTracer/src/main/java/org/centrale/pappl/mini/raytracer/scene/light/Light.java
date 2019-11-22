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
public abstract class Light {
    private Vector3 lightColor;
    private double intensity;

    protected Light(Vector3 lightColor, double intensity) {
        this.lightColor = lightColor;
        this.intensity = intensity;
    }

    public Vector3 getLightColor() {
        return lightColor;
    }

    public double getIntensity(){
        return intensity;
    }
    
    public void setIntensity(double newIntensity){
        this.intensity = newIntensity;
    }
}