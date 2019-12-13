/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.object;

import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Sphere extends SceneObject {
    
    //ATTRIBUTES
    /**
     * Sphere's center
     */
    private final Vector3 center;
    
    /**
     * Sphere's radius
     */
    private final double radius;
    
    /**
     * Square of the sphere's radius
     */
    private final double radiusSq;
    
    //CONSTRUCTORS
    /**
     *
     * @param center
     * @param radius
     */
    public Sphere(Vector3 center, int radius){
        this.center = center;
        this.radius = radius;
        this.radiusSq = radius*radius;
    }
    
    //OTHER METHODS
    /**
     * Computes ray's intersection with this sphere
     * @param ray
     * @return
     */
    @Override
    public RayCastResult intersect(Ray ray){
        RayCastResult rayCastResult = new RayCastResult();

        /*
            O <- Origine du lancé de rayon
            u <- Vecteur unitaire direction du rayon
            C <- Centre de la sphere
            r <- Rayon de la sphere
            P = Proj[u](vec(OC)) = (COMME U EST UNITAIRE) (u . OC) u

            I = u x (||P|| - sqrt(r² - ||PC||²))
         */

        Vector3 OC = center.subtract(ray.getOrigin());
        Vector3 OP = ray.getDirection().scale(OC.dot(ray.getDirection()));
        Vector3 PC = OP.subtract(OC);

        double mPCSq = PC.magnitudeSq();

        if (mPCSq > radiusSq) return rayCastResult;

        rayCastResult.setResult(ray.getDirection().scale(OP.magnitude() - Math.sqrt(radiusSq - mPCSq)), this);
        rayCastResult.hit = true;
        return rayCastResult;
    }
    
    /**
     * Computes the normal vector at the position on this object
     * @param position
     * @return
     */
    @Override
    public Vector3 getNormal(Vector3 position){
        return position.subtract(this.center).normalized();
    }
}
