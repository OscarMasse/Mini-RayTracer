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
import org.centrale.pappl.mini.raytracer.scene.object.TriangleMeshBox;

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
        SceneObject sceneObject = new Sphere(new Vector3(400, 100, -1100), 300);
        sceneObject.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        scene.addObject(sceneObject);

        SceneObject sceneObject2 = new Sphere(new Vector3(0, 100, -1050), 50);
        sceneObject2.setColor(new Vector3((float) 0x6a / 0xFF, (float) 0x40 / 0xFF, (float) 0x9B / 0xFF));
        scene.addObject(sceneObject2);

        //FIX IT --> rotation fausse, la face du fond apparaît?
        SceneObject sceneObject3 = new TriangleMeshBox(new Vector3(-100, 150, -1300), new Vector3(-500, 400, -2000), new Vector3(1, -1, 0).normalized(), new Vector3(1, 1, 0).normalized(), Scene.UZ);
        sceneObject3.setColor(new Vector3((float) 0x73 / 0xFF, (float) 0xab / 0xFF, (float) 0x63 / 0xFF));
        scene.addObject(sceneObject3);

        SceneObject sceneObject4 = new TriangleMeshBox(new Vector3(-400, -250, -1100), new Vector3(-100, -100, -2000), new Vector3(1, 1, 0).normalized(), new Vector3(-1, 1, 0).normalized(), Scene.UZ);
        sceneObject4.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        scene.addObject(sceneObject4);
        
        // Fill lights
        DirectionalLight dLight1 = new DirectionalLight(new Vector3(0, 0, -1.5), new Vector3(Color.white), 2);
        scene.addLight(dLight1);

        /**
        DirectionalLight dLight2 = new DirectionalLight(new Vector3(-1, 0), new Vector3(Color.white), 0.6);
        scene.addLight(dLight2);
         */

        PointLight pLight1 = new PointLight(new Vector3(Color.white), 50, new Vector3(0, 0, -1300));
        scene.addLight(pLight1);
        
        PointLight pLight2 = new PointLight(new Vector3(Color.white), 70, new Vector3(0, -200, -1000));
        scene.addLight(pLight2);
        
        PointLight pLight3 = new PointLight(new Vector3(Color.white), 70, new Vector3(-250, 75, -1300));
        scene.addLight(pLight3);

        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){
                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
