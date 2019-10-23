package org.centrale.pappl.mini.raytracer;

public abstract class Object {
    public abstract boolean intersect(Ray ray);
    public abstract Vector3 getNormal(Vector3 position);
}
