/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import java.awt.Color;

/**
 *
 * @author skiara
 */
public class Raster {
    
    //ATTRIBUTES
    
    /**
     * Raster image's origin 
     */
    private Vector3 origin;
    
    /**
     * Raster image's diagonal vector
     */
    private Vector3 diagonal;
    
    /**
     * Raster image: board of pixels filled with a color
     */
    private Color [][] pixels;
    
    /**
     * Raster image's height
     */
    public final static int HEIGHT = 900;
    
    /**
     *Raster image's width
     */
    public final static int WIDTH = 1600;
    
    //CONSTRUCTOR
    
    /**
     * Constructor
     * 
     * @param origin
     * @param diagonal
     * @param pixels
     */
    public Raster(Vector3 origin, Vector3 diagonal, Color [][] pixels){
        this.origin = origin;
        this.diagonal = diagonal;
        this.pixels = pixels;
    }
    
    /**
     * Default constructor
     */
    public Raster() {
        this.origin = new Vector3();
        this.diagonal = new Vector3();
        this.pixels = new Color[Raster.HEIGHT][Raster.WIDTH];
    }
    
    //GETTERS AND SETTERS

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Vector3 diagonal) {
        this.diagonal = diagonal;
    }

    public Color[][] getPixels() {
        return pixels;
    }

    public void setPixels(Color[][] pixels) {
        this.pixels = pixels;
    }
    
}
