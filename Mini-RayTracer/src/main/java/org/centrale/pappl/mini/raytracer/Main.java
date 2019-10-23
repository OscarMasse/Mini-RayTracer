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
public class Main {
    public static void main(String[] args){
        
        Scene scene = Scene.getScene();
        
        Raster raster = scene.getRaster();
        Camera camera = scene.getCamera();
        
        //Fill objects
        //Fill lights
        
        
        //For each pixel in raster
        for (int i = 0; i < Raster.WIDTH; i++){
            for (int j = 0; j < Raster.HEIGHT; j++){
                Vector3 direction = raster.getOrigin();
                direction.add(raster.getUx().scalarMultiplication(i));
                direction.add(raster.getUy().scalarMultiplication(j));
                
                Ray ray = new Ray(camera.getPosition(), direction);
                for (int k = 0; k < scene.getObjects().size(); k++){
                    //Test for closest intersection: trace() 
                    //if true
                        //fill pixel with object color at intersection
                    // else
                        //fill pixel with background color
                }
                
                    
            }
        }
            
        
        
    }
}
