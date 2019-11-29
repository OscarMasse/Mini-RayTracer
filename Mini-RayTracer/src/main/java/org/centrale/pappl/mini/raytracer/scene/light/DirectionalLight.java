package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

public class DirectionalLight extends Light {

    private Vector3 direction;
    
    public DirectionalLight(Vector3 direction, Vector3 lightColor, double intensity) {
        super(lightColor, intensity);
        this.direction = direction.normalized();
    }
    
    public Vector3 getDirection() {
        return direction;
    }
}
