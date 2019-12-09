/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.object;

import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 *
 * @author Sarah Petrocchi @ECN
 */
public class Triangle extends SceneObject {

    public Vector3 v0;
    public Vector3 v1;
    public Vector3 v2;

    public Triangle(Vector3 v0, Vector3 v1, Vector3 v2) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;

        Vector3 normal = getNormal(v0);
        if (normal.dot(v0) > 0) {
            this.v0 = v2;
            this.v1 = v1;
            this.v2 = v0;
        }

    }

    public Vector3 getNormal(Vector3 position) {
        Vector3 v0v1 = new Vector3(v1.subtract(v0));
        Vector3 v0v2 = new Vector3(v2.subtract(v0));
        return v0v1.cross(v0v2).normalized();
    }

    public RayCastResult intersect(Ray ray) {
        RayCastResult rayCastResult = new RayCastResult();

        Vector3 normal = this.getNormal(v0);

        double normalDotDirection = normal.dot(ray.getDirection());

        //check if ray and plane are parallel
        if (Math.abs(normalDotDirection) < 0) {
            return rayCastResult;
        }

        //plane's parametric equation
        double a = normal.x;
        double b = normal.y;
        double c = normal.z;
        double d = normal.dot(v0);

        //t parameter for intersection on the ray
        //TODO change - sign if necessary
        double t = (normal.dot(ray.getOrigin()) + d) / normalDotDirection;

        //check if the triangle is "behind" the ray
        if (t < 0) {
            return rayCastResult;
        }

        Vector3 intersection = ray.getOrigin().add(ray.getDirection().scale(t));

        //inside/outside test
        if (normal.dot(v1.subtract(v0).cross(intersection.subtract(v0))) < 0) {
            return rayCastResult;
        }
        if (normal.dot(v2.subtract(v1).cross(intersection.subtract(v1))) < 0) {
            return rayCastResult;
        }
        if (normal.dot(v0.subtract(v2).cross(intersection.subtract(v2))) < 0) {
            return rayCastResult;
        }

        if (normal.dot(ray.getDirection()) > 0) {
            return rayCastResult;
        } // back-facing surface 

        rayCastResult.setResult(intersection, this);
        rayCastResult.hit = true;
        return rayCastResult;
    }
}
