/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.object;

import java.util.ArrayList;

import org.centrale.pappl.mini.raytracer.graphics.Matrix3;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.scene.Scene;

/**
 *
 * @author skiara
 */
public class Box extends SceneObject {

    private ArrayList<Vector3> bounds;
    private Matrix3 rotation;

    public Box() {
        this.bounds = new ArrayList<>();
        this.setRotation(Matrix3.getIdentity3());
    }

    public Box(Vector3 vmin, Vector3 vmax) {
        this();
        if(vmin.magnitude() < vmax.magnitude()) {
            this.bounds.add(vmin);
            this.bounds.add(vmax);
        } else {
            this.bounds.add(vmax);
            this.bounds.add(vmin);
        }
    }

    public Matrix3 getRotation() {
        return rotation;
    }

    public ArrayList<Vector3> getBounds() {
        return bounds;
    }

    public void setBounds(ArrayList<Vector3> bounds) {
        this.bounds = bounds;
    }

    public void swap(float a, float b) {
        float c = a;
        a = b;
        b = c;
    }

    public void setRotation(Matrix3 m) {
        this.rotation = new Matrix3(m);
    }

    public void rotate(Vector3 axis, double angle) {
        this.setRotation(rotation.rotate(axis, angle));
    }

    @Override
    public RayCastResult intersect(Ray ray) {
        ray = ray.rotate(rotation.transpose());
        RayCastResult rayCastResult = new RayCastResult();
        Vector3 min = this.bounds.get(0);
        Vector3 max = this.bounds.get(1);

        float tmin = (float) ((min.x - ray.getOrigin().x) / ray.getDirection().x);
        float tmax = (float) ((max.x - ray.getOrigin().x) / ray.getDirection().x);

        if (tmin > tmax) {
            swap(tmin, tmax);
        }

        float tymin = (float) ((min.y - ray.getOrigin().y) / ray.getDirection().y);
        float tymax = (float) ((max.y - ray.getOrigin().y) / ray.getDirection().y);

        if (tymin > tymax) {
            swap(tymin, tymax);
        }

        if ((tmin > tymax) || (tymin > tmax)) {
            return rayCastResult;
        }

        if (tymin > tmin) {
            tmin = tymin;
        }

        if (tymax < tmax) {
            tmax = tymax;
        }

        float tzmin = (float) ((min.z - ray.getOrigin().z) / ray.getDirection().z);
        float tzmax = (float) ((max.z - ray.getOrigin().z) / ray.getDirection().z);

        if (tzmin > tzmax) {
            swap(tzmin, tzmax);
        }

        if ((tmin > tzmax) || (tzmin > tmax)) {
            return rayCastResult;
        }

        if (tzmin > tmin) {
            tmin = tzmin;
        }

        if (tzmax < tmax) {
            tmax = tzmax;
        }

        //à vérifier
        Vector3 result = new Vector3(ray.getDirection().scale(tmin));
//        rayCastResult.setResult(result, this);
//        System.out.println(result.x + " " + result.y + " " + result.z);
        result = rotation.multiply(result);
//        System.out.println(result.x + " " + result.y + " " + result.z);
        rayCastResult.setResult(result, this);

        return rayCastResult;
    }

    @Override
    public Vector3 getNormal(Vector3 position) {
        Vector3 normal = new Vector3();

        if (Math.round(position.x)== this.bounds.get(0).x) {
            normal = new Vector3(Scene.UX.scale(-1));
        }
        else if (Math.round(position.y) == this.bounds.get(0).y) {
            normal = new Vector3(Scene.UY.scale(-1));
        }
        else if (Math.round(position.z) == this.bounds.get(0).z) {
            normal = new Vector3(Scene.UZ);
        }

        else if (Math.round(position.x) == this.bounds.get(1).x) {
            normal = new Vector3(Scene.UX);
        }

        else if (Math.round(position.y) == this.bounds.get(1).y) {
            normal = new Vector3(Scene.UY);
        }

        else if (Math.round(position.z) == this.bounds.get(1).z) {
            normal = new Vector3(Scene.UZ.scale(-1));
        }
        return rotation.multiply(normal);
    }
}
