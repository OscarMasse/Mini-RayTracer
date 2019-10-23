package org.centrale.pappl.mini.raytracer;

import java.awt.*;

public abstract class Object {

    private Color color;

    public abstract Vector3 intersect(Ray ray);

    public abstract Vector3 getNormal(Vector3 position);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
