/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Oscar Masse & Sarah Petrocchi @ECN
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
    private Vector3[][] pixels;

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
     * @param ux
     * @param uy
     * @param pixels
     */
    public Raster(Vector3 origin, Vector3 ux, Vector3 uy, Vector3[][] pixels) {
        this.origin = origin;
        this.ux = ux;
        this.uy = uy;
        this.pixels = pixels;
    }

    /**
     * Default constructor
     */
    public Raster() {
        this.origin = new Vector3(-WIDTH/2, -HEIGHT/2, -1000);
        this.ux = new Vector3(1, 0);
        this.uy = new Vector3(0, 1);
        this.pixels = new Vector3[Raster.WIDTH][Raster.HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                pixels[i][j] = new Vector3();
            }
        }
    }

    //GETTERS AND SETTERS

    /**
     *
     * @return
     */

    public Vector3 getOrigin() {
        return origin;
    }

    /**
     *
     * @param origin
     */
    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    /**
     *
     * @return
     */
    public Vector3 getUx() {
        return ux;
    }

    /**
     *
     * @param ux
     */
    public void setUx(Vector3 ux) {
        this.ux = ux;
    }

    /**
     *
     * @return
     */
    public Vector3 getUy() {
        return uy;
    }

    /**
     *
     * @param uy
     */
    public void setUy(Vector3 uy) {
        this.uy = uy;
    }

    /**
     *
     * @return
     */
    public Vector3[][] getPixels() {
        return pixels;
    }

    /**
     * Adds chosen color to chosen place in pixels
     * @param i
     * @param j
     * @param color
     */
    public void colorize(int i, int j, Vector3 color) {
        pixels[i][j] = color;
    }

    /**
     * Computes buffered image
     * @throws IOException
     */
    public void print() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                bufferedImage.setRGB(i, HEIGHT - j - 1, pixels[i][j].toRGB());
            }
        }
        File outputFile = new File("image.png");
        ImageIO.write(bufferedImage, "png", outputFile);

    }
}
