package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class AmbientLight extends Light {

    //CONSTRUCTORS
    /**
     *
     */
    public AmbientLight() {
        this(new Vector3(0, 0, 0), 1f);
    }

    /**
     *
     * @param lightColor
     * @param intensity
     */
    public AmbientLight(Vector3 lightColor, double intensity) {
        super(lightColor, intensity);
    }
}
