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
        SceneObject sceneObject = new Sphere(new Vector3(300, 450, 1100), 50);
        sceneObject.setColor(new Vector3(Color.magenta));
        scene.addObject(sceneObject);

        SceneObject sceneObject2 = new Sphere(new Vector3(-500, 100, 1100), 300);
        sceneObject2.setColor(new Vector3(Color.cyan));
        scene.addObject(sceneObject2);

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
