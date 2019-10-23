/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author skiara
 */
public class Raster {

    //ATTRIBUTES

    /**
     * Raster image's origin
     */
    private Vector3 origin;

    /**
     * Raster image's horizontal vector
     */
    private Vector3 ux;

    /**
     * Raster image's vertical vector
     */
    private Vector3 uy;

    /**
     * Raster image: board of pixels filled with a color
     */
    private Color[][] pixels;

    /**
     * Raster image's height
     */
    public final static int HEIGHT = 900;

    /**
     * Raster image's width
     */
    public final static int WIDTH = 1600;

    //CONSTRUCTOR

    /**
     * Constructor
     *
     * @param origin
     * @param pixels
     */
    public Raster(Vector3 origin, Vector3 ux, Vector3 uy, Color[][] pixels) {
        this.origin = origin;
        this.ux = ux;
        this.uy = uy;
        this.pixels = pixels;
    }

    /**
     * Default constructor
     */
    public Raster() {
        this.origin = new Vector3();
        this.ux = new Vector3();
        this.uy = new Vector3();
        this.pixels = new Color[Raster.HEIGHT][Raster.WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                pixels[i][j] = Color.black;
            }
        }
    }

    //GETTERS AND SETTERS

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getUx() {
        return ux;
    }

    public void setUx(Vector3 ux) {
        this.ux = ux;
    }

    public Vector3 getUy() {
        return uy;
    }

    public void setUy(Vector3 uy) {
        this.uy = uy;
    }

    public Color[][] getPixels() {
        return pixels;
    }


    public void colorize(int i, int j, Color color) {
        pixels[i][j] = color;
    }

    public void print() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                bufferedImage.setRGB(i, j, pixels[i][j].getRGB());
            }
        }
        File outputFile = new File("image.jpg");
        ImageIO.write(bufferedImage, "jpg", outputFile);

    }
}
