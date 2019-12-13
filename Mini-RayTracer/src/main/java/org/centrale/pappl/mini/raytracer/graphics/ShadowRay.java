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
    private final Ray previousRay;
    private final Vector3 origin;
    private final Vector3 direction;
    private final double distanceToLight;

    public ShadowRay(SceneObject sceneObject, Light focusedLight, Ray ray, Vector3 origin) {
        this.sceneObject = sceneObject;
        this.material = sceneObject.getMaterial();
        this.focusedLight = focusedLight;
        this.previousRay = ray;
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
//        System.out.println(direction);
//        System.out.println("distanceToLight = " + distanceToLight);
    }

    public Vector3 getIllumination() {

        // Detection of the closest hit object
        RayCastResult rayCastResult;
        for (SceneObject object : Scene.getScene().getSceneObjects()) {
            System.out.println("sceneObject = " + object);
            rayCastResult = object.intersect(this);
            System.out.println(rayCastResult.hit);
            if (rayCastResult.hit && !sceneObject.equals(rayCastResult.hitObject)) {
                double objectDistance = rayCastResult.intersection.magnitude();
                System.out.println("Shadow ray hit!");
//                System.out.println(objectDistance + " " + distanceToLight);
                if (objectDistance < distanceToLight) {
//                    System.out.println("objectDistance = " + objectDistance);
//                    System.out.println("distanceToLight = " + distanceToLight);
//                    System.out.println(this.direction);
//                    if (rayCastResult.hitObject.equals(object)) System.out.println("Hit itself");
                    return new Vector3();
                }
            }
        }

        // If no hit object, compute illumination
        Vector3 illumination = new Vector3();

//        Vector3 Lm = new Vector3();
//        if (focusedLight instanceof DirectionalLight) Lm = direction.normalized();
//        if (focusedLight instanceof PointLight) Lm = ((PointLight) focusedLight).getPosition().subtract(sceneObject.getNormal(origin)).normalized();
        Vector3 Lm = this.direction;
        Vector3 N = this.sceneObject.getNormal(this.origin);
        Vector3 Rm = N.scale(2 * Lm.dot(N)).subtract(Lm).normalized();
        Vector3 V = previousRay.getDirection().scale(-1);

        double ks = material.getKs();
        double kd = material.getKd();
        double alpha = material.getAlpha();

        double LmDotN = Lm.dot(N);
//        System.out.println("LmDotN = " + LmDotN);

        Vector3 diffuse = focusedLight.getId().scale(kd).scale(Math.max(0, LmDotN));
        Vector3 specular = focusedLight.getIs().scale(ks).scale(Math.pow((Rm.dot(V)), alpha));


        illumination = illumination
                .add(diffuse.multiply(sceneObject.getColor()))
                .add(specular)
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
