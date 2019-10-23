/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import java.awt.*;
import java.io.IOException;

/**
 *
 * @author skiara
 */
public class Main {
    public static void main(String[] args) throws IOException {
        
        Scene scene = Scene.getScene();
        
        Raster raster = scene.getRaster();
        Camera camera = scene.getCamera();

        RayTracer rayTracer = new RayTracer();

        // Fill objects
        Object object = new Sphere(new Vector3(300, 300, 100), 100);
        object.setColor(Color.BLUE);
        scene.addObject(object);

//        Object object2 = new Sphere(new Vector3(20, 50, 100), 1);
//        object2.setColor(Color.BLUE);
//        scene.addObject(object2);

        // Fill lights
        
        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){

                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
