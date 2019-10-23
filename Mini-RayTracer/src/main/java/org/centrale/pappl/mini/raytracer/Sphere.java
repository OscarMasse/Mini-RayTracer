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
public class Sphere extends SceneObject {
    private final Vector3 center;
    private final double radius;
    private final double radiusSq;
    
    public Sphere(Vector3 center, int radius){
        this.center = center;
        this.radius = radius;
        this.radiusSq = radius*radius;
}
    
    @Override
    public boolean intersect(Ray ray, Vector3 intersection){

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

        if (mPCSq > radiusSq) return false;

        intersection.set(ray.getDirection().scale(OP.magnitude() - Math.sqrt(radiusSq - mPCSq)));

        return true;

//        Vector3 cameraCenter = this.center.subtract(ray.getOrigin());
//
//        double tca = cameraCenter.dot(ray.getDirection());
//        tca = tca / ray.getDirection().magnitude();
//
//        double d2 = cameraCenter.dot(cameraCenter) - tca*tca;
//
//        if (d2 > radius*radius) return intersection;
//
//        double thc = sqrt(radius*radius - d2);
//        double distanceToCamera = tca - thc;
//
//        Vector3 intersectionDirection = ray.getOrigin().add(ray.getDirection().normalized().scale(distanceToCamera));
//
//        intersection = new Vector3(intersectionDirection.dot(Scene.UX), intersectionDirection.dot(Scene.UY), intersectionDirection.dot(Scene.UZ));
//
//        return intersection;
    }
    
    @Override
    public Vector3 getNormal(Vector3 position){
        Vector3 normal = new Vector3();
        return normal;
    }
    
}
