package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

public class DirectionalLight extends Light {

    public DirectionalLight(Vector3 direction, Vector3 lightColor, double intensity) {
        super(direction, lightColor, intensity);
    }
}
