/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import org.centrale.pappl.mini.raytracer.scene.Raster;
import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

import java.awt.*;

/**
 *
 * @author skiara
 */
public class RayTracer {

    RayTracer(){
//        shortestDistance = Double.MAX_VALUE;
    }

    public void trace(Raster raster, int i, int j){

        SceneObject closestSceneObject = null;
        double shortestDistance = Double.MAX_VALUE;
        RayCastResult rayCastResult = null;
        RayCastResult hitRayCastResult = null;

        // Construction of the ray passing through the pixel (i,j)
        Vector3 direction;
        direction = raster.getOrigin()
                .add(raster.getUx().scale(i))
                .add(raster.getUy().scale(j));
        Ray ray = new Ray(Scene.getScene().getCamera().getPosition(), direction.subtract(Scene.getScene().getCamera().getPosition()));

        // Testing intersection with each object of the scene
        for (SceneObject sceneObject : Scene.getScene().getSceneObjects()) {
            rayCastResult = sceneObject.intersect(ray);
            if (rayCastResult.hit) {
                double objectDistance = rayCastResult.intersection.magnitude();
                if (objectDistance < shortestDistance) {
                    closestSceneObject = sceneObject;
                    shortestDistance = objectDistance;
                    hitRayCastResult = rayCastResult;
                }
            }
        }
        if (closestSceneObject != null) {
            Vector3 color = new Vector3();
                for (Light light : Scene.getScene().getLights()) {
                    color = color.add(closestSceneObject.getColor()
                            .add(light.getLightColor().scale(-light.getIntensity() * hitRayCastResult.normal.dot(light.getDirection()) - 1))
                            .clamp())
                            .clamp();
                }
            raster.colorize(i, j, color);
        }
    }
}
