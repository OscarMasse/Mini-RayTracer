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
public class PointLight extends Light {

    //ATTRIBUTES
    
    /**
     * Light's position
     */
    private Vector3 position;

    //CONSTRUCTORS
    
    /**
     *
     * @param lightColor
     * @param intensity
     * @param position
     */
    public PointLight(Vector3 lightColor, double intensity, Vector3 position) {
        super(lightColor, intensity);
        this.position = position;
    }

    //GETTERS AND SETTERS
    
    /**
     *
     * @return
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     *
     * @param intersection
     * @return
     */
    public Vector3 getDirection(Vector3 intersection) {
        Vector3 direction = new Vector3(this.position.subtract(intersection));

        return direction;
    }

    /**
     *
     * @param point
     * @return
     */
    public double getIntensity(Vector3 point) {
        double intensity = this.getIntensity() / (4.0 * Math.PI * this.getDirection(point).magnitudeSq());
        return intensity*10000;
    }
}
