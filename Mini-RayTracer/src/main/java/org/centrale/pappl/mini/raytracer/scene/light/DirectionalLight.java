package org.centrale.pappl.mini.raytracer.scene.light;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class DirectionalLight extends Light {

    //ATTRIBUTES
    
    /**
     * Directional light's direction
     */
    private Vector3 direction;
   
    //CONSTRUCTORS
    
    /**
     *
     * @param direction
     * @param lightColor
     * @param intensity
     */
    public DirectionalLight(Vector3 direction, Vector3 lightColor, double intensity) {
        super(lightColor, intensity);
        this.direction = direction.normalized();
    }
  
    //GETTERS AND SETTERS
    
    /**
     *
     * @return
     */
    public Vector3 getDirection() {
        return direction;
    }
}
