/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import org.centrale.pappl.mini.raytracer.graphics.Vector3;
import org.centrale.pappl.mini.raytracer.scene.Camera;
import org.centrale.pappl.mini.raytracer.scene.Raster;
import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.AmbientLight;
import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.scene.object.Sphere;
import org.centrale.pappl.mini.raytracer.scene.object.TriangleMeshBox;
import org.centrale.pappl.mini.raytracer.scene.object.materials.Material;

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

        Material material1 = new Material(0.03, 1, 0.1, 1);
        material1.setParameters(1, 0.8, 0);

        Material material2 = new Material(0.03, 1, 0.1, 1);
        material2.setParameters(0.3, 10, 0);

        SceneObject ground = new TriangleMeshBox(new Vector3(-1000, -300, -900), new Vector3(1000, -320, -2000), Scene.UX, Scene.UY, Scene.UZ);
        ground.setColor(new Vector3(0.1, 0.1, 0.1));
        ground.setMaterial(material2);
        scene.addObject(ground);

        SceneObject sceneObject2 = new Sphere(new Vector3(400, 0, -1600), 300);
        sceneObject2.setColor(new Vector3((float) 0x9F / 0xFF, (float) 0x3C / 0xFF, (float) 0x33 / 0xFF));
        sceneObject2.setMaterial(material1);
        scene.addObject(sceneObject2);

        SceneObject sceneObject3 = new Sphere(new Vector3(-50, 0, -1300), 50);
        sceneObject3.setColor(new Vector3((float) 0x6a / 0xFF, (float) 0x40 / 0xFF, (float) 0x9B / 0xFF));
//        sceneObject3.setColor(new Vector3(0, 0.9, 0.9)); // Bug: interaction avec lumière ambiente
        sceneObject3.setMaterial(material1);
        scene.addObject(sceneObject3);

        SceneObject sceneObject4 = new Sphere(new Vector3(-200, -200, -1100), 100);
        sceneObject4.setColor(new Vector3(0.4, 0.8, 0.3));
        sceneObject4.setMaterial(material1);
        scene.addObject(sceneObject4);

        // Fill lights

        Scene.getScene().setAmbientLight(new AmbientLight(new Vector3(0.1, 0.1, 0.1), 1));

        DirectionalLight dLight1 = new DirectionalLight(new Vector3(-1, -1, 1), new Vector3(Color.white), 2);
        scene.addLight(dLight1);

        /**
         DirectionalLight dLight2 = new DirectionalLight(new Vector3(-1, 0), new Vector3(Color.white), 0.6);
         scene.addLight(dLight2);
         */

        PointLight pLight1 = new PointLight(new Vector3(Color.white), 1, new Vector3(-200, 0, -1300));
        scene.addLight(pLight1);

        PointLight pLight2 = new PointLight(new Vector3(Color.white), 1, new Vector3(50, 0, -1300));
        scene.addLight(pLight2);

        PointLight pLight3 = new PointLight(new Vector3(Color.white), 1, new Vector3(400, -280, -1300));
        scene.addLight(pLight3);

        // Test
        /*Ray ray = new Ray(new Vector3(), new Vector3(0, 0, -1));
        RayCastResult rcr = sceneObject2.intersect(ray);
        System.out.println("rcr.intersection = " + rcr.intersection);
        ShadowRay sr = new ShadowRay(sceneObject2, pLight1, ray, rcr.intersection.add(rcr.normal.scale(0.000000000001f)));
        System.out.println("sr.origin = " + sr.getOrigin());
        System.out.println("sr.direction = " + sr.getDirection());

        RayCastResult rcr2 = sceneObject3.intersect(sr);
        System.out.println("hit: " + rcr2.hit);
        double objectDistance = (rcr2.intersection.magnitude());
        System.out.println("intersection = " + rcr2.intersection);
        System.out.println("objectDistance = " + objectDistance);

        System.out.println("pLight color = " + pLight1.getLightColor());
        Vector3 v = sr.getIllumination();
        System.out.println("illumination = " + v);*/

        // For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++) {
            for (int j = 0; j < Raster.HEIGHT; j++) {
                rayTracer.trace(raster, i, j);
            }
        }
        raster.print();
    }
}
