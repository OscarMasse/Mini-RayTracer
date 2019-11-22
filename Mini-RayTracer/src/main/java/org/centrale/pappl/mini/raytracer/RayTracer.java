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
import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;

/**
 *
 * @author skiara
 */
public class RayTracer {

    RayTracer() {
//        shortestDistance = Double.MAX_VALUE;
    }

    public void trace(Raster raster, int i, int j) {

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
                if (light instanceof DirectionalLight) {
                    double hitAngle = hitRayCastResult.normal.dot(((DirectionalLight) light).getDirection(hitRayCastResult.intersection));
                    double intensity = -light.getIntensity();
                    Vector3 vector = light.getLightColor().scale(intensity * hitAngle -1);
                    
                    color = color.add(closestSceneObject.getColor().multiply(vector)).clamp();
                }
                if (light instanceof PointLight) {
                    
                    double hitAngle = hitRayCastResult.normal.dot(((PointLight) light).getDirection(hitRayCastResult.intersection).normalized());
                    double intensity = ((PointLight) light).getIntensity(hitRayCastResult.intersection);
                    Vector3 lightColor = light.getLightColor().scale(intensity* hitAngle);
                    
                    Vector3 vector = closestSceneObject.getColor().multiply(lightColor);    
                    color = color.add(vector).clamp();
                }
            }
            raster.colorize(i, j, color);
        }
    }
}
