/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.object;

import java.util.ArrayList;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;

/**
 *
 * @author skiara
 */
public class Box extends SceneObject {

    private ArrayList<Vector3> bounds;

    public void Box3(Vector3 vmin, Vector3 vmax) {
        this.bounds.add(vmin);
        this.bounds.add(vmax);
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

    @Override
    public boolean intersect(Ray ray, RayCastResult rayCastResult) {
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
            return false;
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
            return false;
        }

        if (tzmin > tmin) {
            tmin = tzmin;
        }

        if (tzmax < tmax) {
            tmax = tzmax;
        }

        //à vérifier
        rayCastResult.setResult(new Vector3(ray.getOrigin().add(ray.getDirection().scale(tmin))), this);

        return true;
    }

    @Override
    public Vector3 getNormal(Vector3 position) {
        Vector3 normal = new Vector3();
        if (position.subtract(this.bounds.get(0)).magnitude() < position.subtract(this.bounds.get(1)).magnitude()) {
            if (position.x == this.bounds.get(0).x && position.y == this.bounds.get(0).y) {
                //coordonnée z est vector normal
            }

            if (position.y == this.bounds.get(0).y && position.z == this.bounds.get(0).z) {
                //coordonnée x est vector normal
            }
            
            if (position.z == this.bounds.get(0).z && position.x == this.bounds.get(0).x) {
                //coordonnée y est vector normal
            }
        }
        else {
            if (position.x == this.bounds.get(1).x && position.y == this.bounds.get(1).y) {
                //coordonnée z est vector normal
            }

            if (position.y == this.bounds.get(1).y && position.z == this.bounds.get(1).z) {
                //coordonnée x est vector normal
            }
            
            if (position.z == this.bounds.get(1).z && position.x == this.bounds.get(1).x) {
                //coordonnée y est vector normal
            }
        }
        return normal;
    }
}
