package org.centrale.pappl.mini.raytracer;

import java.awt.*;

public abstract class SceneObject {

    private Vector3 color;

    public abstract boolean intersect(Ray ray, Vector3 intersection);

    public abstract Vector3 getNormal(Vector3 position);

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public Vector3 getColor() {
        return this.color;
    }
}
