/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Camera {

    private static Camera camera;

    //ATTRIBUTES
    
    /**
     * Camera's position
     */
    @SuppressWarnings("FieldMayBeFinal")
    private Vector3 position;
    
    //CONSTRUCTORS
    
    /**
     * Constructor
     * @param position 
     */
    public Camera(Vector3 position){
        this.position = position;
    }
    
    /**
     * Default constructor
     */
    private Camera(){
        this.position = new Vector3();
    }
    
    //GETTERS AND SETTERS

    /**
     *
     * @return
     */

    public static Camera getCamera() {
        if (camera == null) Camera.camera = new Camera();
        return camera;
    }

    /**
     *
     * @return
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }
    
}
