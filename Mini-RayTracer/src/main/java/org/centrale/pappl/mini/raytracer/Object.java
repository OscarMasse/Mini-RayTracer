package org.centrale.pappl.mini.raytracer;

public interface Object {
    public boolean intersect(Ray ray);
    public Vector3 getNormal(Vector3 position);
}
