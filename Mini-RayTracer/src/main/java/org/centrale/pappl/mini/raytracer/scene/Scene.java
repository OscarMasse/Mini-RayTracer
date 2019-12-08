package org.centrale.pappl.mini.raytracer.scene;

import org.centrale.pappl.mini.raytracer.scene.light.AmbientLight;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

import java.util.ArrayList;

public class Scene {
    private static Scene scene;

    private ArrayList<SceneObject> sceneObjects;
    private ArrayList<Light> lights;

    private Camera camera = Camera.getCamera();
    private Raster raster = new Raster();
    private AmbientLight ambientLight;

    public final static Vector3 UX = new Vector3(1, 0, 0);
    public final static Vector3 UY = new Vector3(0, 1, 0);
    public final static Vector3 UZ = new Vector3(0, 0, 1);


    private Scene() {
        sceneObjects = new ArrayList<>();
        lights = new ArrayList<>();
        ambientLight = new AmbientLight();
    }

    public static Scene getScene() {
        if (scene == null) Scene.scene = new Scene();
        return scene;
    }

    public ArrayList<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void setSceneObjects(ArrayList<SceneObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Raster getRaster() {
        return raster;
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }


    public void addObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public Light getAmbientLight() {
        return this.ambientLight;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }
}

