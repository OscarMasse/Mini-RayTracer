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

    RayTracer(){}
    
    public void trace(Raster raster, int i, int j){

        Vector3 direction = raster.getOrigin();
        direction.add(raster.getUx().scalarMultiplication(i));
        direction.add(raster.getUy().scalarMultiplication(j));

        Ray ray = new Ray(Scene.getScene().getCamera().getPosition(), direction);
        for (int k = 0; k < Scene.getScene().getObjects().size(); k++){
            // Test for closest intersection: trace()
            // if true
            // fill pixel with object color at intersection
            // else
            // fill pixel with background color
        }
        
    }
}
