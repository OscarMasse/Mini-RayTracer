package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;

public class RayCastResult {
    public Vector3 intersection;
    public SceneObject hitObject;
    public Vector3 normal;
    public boolean hit;

    public RayCastResult(){
        this.hit = false;
    }

    public RayCastResult(Vector3 intersection, SceneObject hitObject) {
        this.intersection = intersection;
        this.hitObject = hitObject;
        this.normal = hitObject.getNormal(intersection);
    }

    public void setResult(Vector3 intersection, SceneObject hitObject) {
        this.intersection = intersection;
        if (hitObject != null) {
            this.hitObject = hitObject;
            this.hit = true;
            this.normal = hitObject.getNormal(intersection);
        }
    }
}
