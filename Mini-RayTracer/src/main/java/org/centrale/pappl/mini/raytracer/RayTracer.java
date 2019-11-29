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

import java.util.ArrayList;

import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;
import org.centrale.pappl.mini.raytracer.scene.object.materials.Material;

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

        // Computing the light to determine the color perceived by the camera.
        if (closestSceneObject != null) {
            ArrayList<Light> visibleLights = new ArrayList<>();
            Vector3 illumination = new Vector3();
            Vector3 color = new Vector3();

            for (Light light : Scene.getScene().getLights()) {
                if (light instanceof DirectionalLight) {
                    // Shadow Rays
                    Vector3 shadowOrigin;
                    Vector3 shadowDirection;

                    shadowOrigin = new Vector3(hitRayCastResult.intersection);
                    shadowDirection = new Vector3(((DirectionalLight) light).getDirection()).normalized();

                    Ray shadowRay = new Ray(shadowOrigin, shadowDirection);

                    RayCastResult shadowRCR = null;
                    for (SceneObject sceneObject :
                            Scene.getScene().getSceneObjects()) {
                        shadowRCR = sceneObject.intersect(ray);
                    }
                    if (shadowRCR != null && !shadowRCR.hit) {
                        visibleLights.add(light);
                    }
                }
                if (light instanceof PointLight) {
                    Vector3 shadowOrigin;
                    Vector3 shadowDirection;

                    shadowOrigin = new Vector3(hitRayCastResult.intersection);
                    shadowDirection = ((PointLight) light).getPosition().subtract(shadowOrigin).normalized();

                    Ray shadowRay = new Ray(shadowOrigin, shadowDirection);

                    RayCastResult shadowRCR = null;
                    for (SceneObject sceneObject :
                            Scene.getScene().getSceneObjects()) {
                        shadowRCR = sceneObject.intersect(ray);
                    }
                    if (shadowRCR != null && !shadowRCR.hit) {
                        visibleLights.add(light);
                    }
                }

                // Applying Phong reflection model
                Material material = closestSceneObject.getMaterial();

                illumination = Scene.getScene().getAmbiantLight().getLightColor().scale(material.getKa());

                for (Light li : visibleLights) {
                    Vector3 is = li.getIs();
                    Vector3 id = li.getId();

                    Vector3 Lm = new Vector3();
                    if (li instanceof DirectionalLight) Lm = new Vector3(((DirectionalLight) li).getDirection()).normalized();
                    if (li instanceof PointLight) Lm = ((PointLight) li).getPosition().subtract(hitRayCastResult.intersection).normalized();
                    Vector3 N = hitRayCastResult.normal.normalized();
                    Vector3 Rm = N.scale(2).scale(N.dot(Lm)).subtract(Lm).normalized();
                    Vector3 V = ray.getDirection().scale(-1).normalized();

                    double ks = material.getKs();
                    double kd = material.getKd();
                    double alpha = material.getAlpha();

                    illumination = illumination
                            .add(id.scale(kd).scale(Lm.dot(N)))
                            .add(is.scale(ks).scale(Math.pow(Math.max(0, Rm.dot(V)), alpha)))
                            .clamp();

                    Vector3 vector = closestSceneObject.getColor().multiply(illumination);
                    color = new Vector3().add(vector).clamp();
                }
            }
            raster.colorize(i, j, color);
            visibleLights.clear();
        }

/*        // Computing the light to determine the color perceived by the camera.
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
        }*/
    }
}
