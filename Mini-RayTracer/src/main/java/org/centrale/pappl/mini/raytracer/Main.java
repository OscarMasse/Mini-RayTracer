/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import org.centrale.pappl.mini.raytracer.scene.Camera;
import org.centrale.pappl.mini.raytracer.scene.Raster;
import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.AmbientLight;
import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.scene.object.Sphere;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;
import java.awt.*;
import java.io.IOException;
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;
import org.centrale.pappl.mini.raytracer.scene.object.TriangleMeshBox;
import org.centrale.pappl.mini.raytracer.scene.object.materials.Material;

/**
 * Main class
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Main {

    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scene scene = Scene.getScene();

        Raster raster = scene.getRaster();
        Camera camera = scene.getCamera();

        RayTracer rayTracer = new RayTracer();

        // Fill objects
        
        Material material1 = new Material(0.3, 1, 1, 1);
        Material material2 = new Material(0.3, 0.3, 1, 1);
        
        SceneObject sceneObject2 = new Sphere(new Vector3(400, 0, -1500), 300);
        sceneObject2.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        sceneObject2.setMaterial(material2);
        scene.addObject(sceneObject2);

        SceneObject sceneObject3 = new Sphere(new Vector3(500, 100, -1100), 50);
        sceneObject3.setColor(new Vector3((float) 0x6a / 0xFF, (float) 0x40 / 0xFF, (float) 0x9B / 0xFF));
        //sceneObject3.setColor(new Vector3(0, 0.9, 0.9)); // Bug: interaction avec lumière ambiente
        sceneObject3.setMaterial(material2);
        scene.addObject(sceneObject3);

        SceneObject sceneObject5 = new TriangleMeshBox(new Vector3(-700, 200, -1300), new Vector3(-100, 400, -2000), new Vector3(1, -1, 0).normalized(), new Vector3(1, 1, 0).normalized(), Scene.UZ);
        sceneObject5.setColor(new Vector3((float) 0x73 / 0xFF, (float) 0xab / 0xFF, (float) 0x63 / 0xFF));
        sceneObject5.setMaterial(material2);
        scene.addObject(sceneObject5);

        SceneObject sceneObject6 = new TriangleMeshBox(new Vector3(-400, -200,-1200), new Vector3(-700, -400, -1500), Scene.UX, Scene.UY, Scene.UZ);
        sceneObject6.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        sceneObject6.setMaterial(material2);
        scene.addObject(sceneObject6);

        
        // Fill lights

        Scene.getScene().setAmbientLight(new AmbientLight(new Vector3(0.5, 0.5, 0.5), 1));
        
        DirectionalLight dLight1 = new DirectionalLight(new Vector3(0, 0, -1.5), new Vector3(Color.white), 2);
        //scene.addLight(dLight1);
        
        DirectionalLight dLight2 = new DirectionalLight(new Vector3(1, 1, -1), new Vector3(Color.white), 2);
//        scene.addLight(dLight2);

        PointLight pLight1 = new PointLight(new Vector3(Color.white), 1, new Vector3(300, 0, -1000));
        scene.addLight(pLight1);
        
        PointLight pLight2 = new PointLight(new Vector3(Color.white), 1, new Vector3(-600, 400, -1400));
        scene.addLight(pLight2);

        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){
                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
