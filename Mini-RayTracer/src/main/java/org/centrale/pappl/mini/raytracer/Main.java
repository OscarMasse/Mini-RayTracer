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
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;
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
        
        Box sceneObject = new Box(new Vector3(250, -100, -1000), new Vector3(450, 200, -2000));
        sceneObject.setColor(new Vector3((float) 0x73 / 0xFF, (float) 0xab / 0xFF, (float) 0x63 / 0xFF));
        sceneObject.rotate(new Vector3(0, 0, 1), 45);
        scene.addObject(sceneObject);
        
        SceneObject sceneObject2 = new Sphere(new Vector3(400, 100, -1500), 300);
        sceneObject2.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        scene.addObject(sceneObject2);

        SceneObject sceneObject3 = new Sphere(new Vector3(0, 100, -1050), 50);
        sceneObject3.setColor(new Vector3((float) 0x6a / 0xFF, (float) 0x40 / 0xFF, (float) 0x9B / 0xFF));
        scene.addObject(sceneObject3);
        
        // Fill lights
        
        DirectionalLight dLight1 = new DirectionalLight(new Vector3(1, 1, -1.5), new Vector3(Color.white), 1);
        scene.addLight(dLight1);


        DirectionalLight dLight2 = new DirectionalLight(new Vector3(-1, 0), new Vector3(Color.white), 0.6);
        scene.addLight(dLight2);
        
//        PointLight pLight1 = new PointLight(new Vector3(Color.white), 10, new Vector3(0, 0, -1000));
//        scene.addLight(pLight1);
        
        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){
                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
