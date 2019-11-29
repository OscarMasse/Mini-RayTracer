package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

public class AmbiantLight extends org.centrale.pappl.mini.raytracer.scene.light.Light {

    public AmbiantLight() {
        this(new Vector3(), 1f);
    }

    public AmbiantLight(Vector3 lightColor, double intensity) {
        super(lightColor, intensity);
    }
}
