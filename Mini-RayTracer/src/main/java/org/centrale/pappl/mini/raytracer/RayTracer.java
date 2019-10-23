/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

/**
 *
 * @author skiara
 */
public class RayTracer {

    public Object closestObject;
    private double shortestDistance;

    RayTracer(){
        shortestDistance = Double.MAX_VALUE;
    }

    public void trace(Raster raster, int i, int j){

        // Construction of the ray passing through the pixel (i,j)
        Vector3 direction = raster.getOrigin();
        direction.add(raster.getUx().scalarMultiplication(i));
        direction.add(raster.getUy().scalarMultiplication(j));
        Ray ray = new Ray(Scene.getScene().getCamera().getPosition(), direction);

        // Testing intersection with each object of the scene
        for (Object object : Scene.getScene().getObjects()) {
            Vector3 intersectionPoint = object.intersect(ray);
            if (!intersectionPoint.isNullVector()) {
                double objectDistance = intersectionPoint.subtract(ray.getDirection()).magnitude();
                if (objectDistance < shortestDistance) {
                    closestObject = object;
                    shortestDistance = objectDistance;
                }
            }
        }
        if (closestObject != null) {
            raster.colorize(i, j, closestObject.getColor());
        }
    }
}
