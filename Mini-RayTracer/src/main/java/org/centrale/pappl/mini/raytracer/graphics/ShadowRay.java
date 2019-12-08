package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.scene.Scene;
import org.centrale.pappl.mini.raytracer.scene.light.DirectionalLight;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.light.PointLight;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.scene.object.materials.Material;

public class ShadowRay {

    private final SceneObject sceneObject;
    private final Material material;
    private final Light focusedLight;
    private final Vector3 origin;
    private final Vector3 direction;
    private final double distanceToLight;

    public ShadowRay(SceneObject sceneObject, Light focusedLight, Vector3 origin) {
        this.sceneObject = sceneObject;
        this.material = sceneObject.getMaterial();
        this.focusedLight = focusedLight;
        this.origin = origin;
        Vector3 direction = new Vector3();
        if (focusedLight instanceof DirectionalLight) {
            direction = new Vector3(((DirectionalLight) focusedLight).getDirection()).scale(-1);
            distanceToLight = Double.MAX_VALUE;
        } else if (focusedLight instanceof PointLight) {
            direction = ((PointLight) focusedLight).getPosition().subtract(origin);
            distanceToLight = direction.magnitude();
        } else {
            distanceToLight = Double.MAX_VALUE;
        }
        this.direction = direction.normalized();
    }

    public Vector3 getIllumination() {

        // Detection of the closest hit object
        RayCastResult rayCastResult;
        for (SceneObject sceneObject : Scene.getScene().getSceneObjects()) {
            rayCastResult = sceneObject.intersect(this);
            if (rayCastResult.hit) {
                double objectDistance = rayCastResult.intersection.subtract(origin).magnitude();
                if (objectDistance < distanceToLight) {
//                    System.out.println("objectDistance = " + objectDistance);
//                    System.out.println("distanceToLight = " + distanceToLight);
//                    System.out.println("Shadow ray hit!");
//                    System.out.println(this.direction);
                    return new Vector3();
                }
            }
        }

        // If no hit object, compute illumination
        Vector3 illumination = new Vector3();

//        Vector3 Lm = new Vector3();
//        if (focusedLight instanceof DirectionalLight) Lm = direction.normalized();
//        if (focusedLight instanceof PointLight) Lm = ((PointLight) focusedLight).getPosition().subtract(sceneObject.getNormal(origin)).normalized();
        Vector3 Lm = this.direction.normalized();
        Vector3 N = this.sceneObject.getNormal(this.origin).normalized();
        Vector3 Rm = N.scale(2).scale(Lm.dot(N)).subtract(Lm).normalized();
        Vector3 V = Scene.getScene().getCamera().getPosition().add(this.origin).normalized();

        double ks = material.getKs();
        double kd = material.getKd();
        double alpha = material.getAlpha();

        Vector3 diffuse = focusedLight.getId().scale(kd).scale(Math.max(0, Lm.dot(N)));
        Vector3 specular = focusedLight.getIs().scale(ks).scale(Math.pow((Rm.dot(V)), alpha));
        specular.x = diffuse.x > 0 ? specular.x : 0;
        specular.y = diffuse.y > 0 ? specular.y : 0;
        specular.z = diffuse.z > 0 ? specular.z : 0;

        illumination = illumination
//                .add(diffuse)
//                .clamp()
                .add(specular)
//                .clamp()
        ;

        return illumination;
    }

    public Vector3 getOrigin() {
        return this.origin;
    }

    public Vector3 getDirection() {
        return this.direction;
    }
}
