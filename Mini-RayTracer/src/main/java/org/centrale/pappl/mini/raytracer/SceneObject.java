package org.centrale.pappl.mini.raytracer;

import java.awt.*;

public abstract class SceneObject {

    private Color color;

    public abstract boolean intersect(Ray ray, Vector3 intersection);

    public abstract Vector3 getNormal(Vector3 position);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
