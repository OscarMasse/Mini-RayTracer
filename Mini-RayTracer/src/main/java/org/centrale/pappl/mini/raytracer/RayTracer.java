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

//    public Object closestObject;
//    private double shortestDistance;

    RayTracer(){
//        shortestDistance = Double.MAX_VALUE;
    }

    public void trace(Raster raster, int i, int j){

        SceneObject closestSceneObject = null;
        double shortestDistance = Double.MAX_VALUE;

        // Construction of the ray passing through the pixel (i,j)
        Vector3 direction;
        direction = raster.getOrigin()
                .add(raster.getUx().scale(i))
                .add(raster.getUy().scale(j));
        Ray ray = new Ray(Scene.getScene().getCamera().getPosition(), direction.subtract(Scene.getScene().getCamera().getPosition()));

        // Testing intersection with each object of the scene
        for (SceneObject sceneObject : Scene.getScene().getSceneObjects()) {
            Vector3 intersectionPoint = new Vector3();
            if (sceneObject.intersect(ray, intersectionPoint)) {
                double objectDistance = intersectionPoint.magnitude();
                if (objectDistance < shortestDistance) {
                    closestSceneObject = sceneObject;
                    shortestDistance = objectDistance;
                }
            }
        }
        if (closestSceneObject != null) {
            raster.colorize(i, j, closestSceneObject.getColor());
//            closestObject = null;
//            shortestDistance = Double.MAX_VALUE;
        }
    }
}
