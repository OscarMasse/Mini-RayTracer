package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

public class AmbientLight extends org.centrale.pappl.mini.raytracer.scene.light.Light {

    public AmbientLight() {
        this(new Vector3(0, 0, 0), 1f);
    }

    public AmbientLight(Vector3 lightColor, double intensity) {
        super(lightColor, intensity);
    }
}
