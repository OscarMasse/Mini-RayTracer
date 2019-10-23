/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer;

import static java.lang.Math.sqrt;

/**
 *
 * @author skiara
 */
public class Sphere extends Object{
    private final Vector3 center;
    private final double radius;
    
    public Sphere(Vector3 center, int radius){
        this.center = center;
        this.radius = radius;
}
    
    @Override
    public Vector3 intersect(Ray ray){
        Vector3 intersection = new Vector3();
        Vector3 cameraCenter = this.center.subtract(ray.getOrigin());
        
        double tca = cameraCenter.scalarProduct(ray.getDirection());
        if (tca == 0) return intersection;
        
        double d2 = cameraCenter.scalarProduct(cameraCenter) - tca*tca;
        
        if (d2 > radius*radius) return intersection;
        
        double thc = sqrt(radius*radius - d2);
        double distanceToCamera = tca - thc;
        
        Vector3 intersectionDirection = ray.getOrigin();
        intersectionDirection.add(cameraCenter.scalarMultiplication(distanceToCamera));
        
        intersection = new Vector3(intersectionDirection.scalarProduct(Scene.UX), intersectionDirection.scalarProduct(Scene.UY), intersectionDirection.scalarProduct(Scene.UZ));
        
        return intersection;
    }
    
    @Override
    public Vector3 getNormal(Vector3 position){
        Vector3 normal = new Vector3();
        return normal;
    }
    
}
