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
public class PointLight extends Light {

    Vector3 position;

    public PointLight(Vector3 lightColor, double intensity, Vector3 position) {
        super(lightColor, intensity);
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getDirection(Vector3 intersection) {
        Vector3 direction = new Vector3(this.position.subtract(intersection));

        return direction;
    }

    public double getIntensity(Vector3 point) {
        double intensity = this.getIntensity() / (4.0 * Math.PI * this.getDirection(point).magnitudeSq());
        return intensity*10000;
    }
}
