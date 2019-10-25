package org.centrale.pappl.mini.raytracer.scene.object;

import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

public abstract class SceneObject {

    private Vector3 color;

    public abstract boolean intersect(Ray ray, RayCastResult rayCastResult);

    public abstract Vector3 getNormal(Vector3 position);

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public Vector3 getColor() {
        return this.color;
    }
}
