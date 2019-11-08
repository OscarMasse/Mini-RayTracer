/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import org.centrale.pappl.mini.raytracer.scene.Camera;
import org.centrale.pappl.mini.raytracer.scene.Raster;
import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.scene.object.Sphere;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

import java.awt.*;
import java.io.IOException;
import org.centrale.pappl.mini.raytracer.scene.object.Box;

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
        
        SceneObject sceneObject = new Box(new Vector3(0, 0, 1100), new Vector3(50, 50, 1150));
        sceneObject.setColor(new Vector3((float) 0x73 / 0xFF, (float) 0xab / 0xFF, (float) 0x63 / 0xFF));
        scene.addObject(sceneObject);
        /**
        SceneObject sceneObject2 = new Sphere(new Vector3(500, 100, 1100), 300);
        sceneObject2.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        scene.addObject(sceneObject2);

        SceneObject sceneObject3 = new Sphere(new Vector3(300, 100, 900), 50);
        sceneObject3.setColor(new Vector3((float) 0x6a / 0xFF, (float) 0x40 / 0xFF, (float) 0x9B / 0xFF));
        scene.addObject(sceneObject3);
        */
        
        // Fill lights
        DirectionalLight light1 = new DirectionalLight(new Vector3(1, -1, 1.5), new Vector3(Color.white), 1.2);
        scene.addLight(light1);

        DirectionalLight light2 = new DirectionalLight(new Vector3(-1 , 0), new Vector3(Color.white), 0.6);
        scene.addLight(light2);
        

        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){

                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
