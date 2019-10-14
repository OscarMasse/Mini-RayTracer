/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

/**
 *
 * @author skiara
 */
public class Camera {
    
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
    public Camera(){
        this.position = new Vector3();
    }
    
    //GETTERS AND SETTERS
    
    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
    
}
