package org.centrale.pappl.mini.raytracer;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Scene {
    private static Scene scene;

    private ArrayList<Object> objects;
    private ArrayList<Light> lights;

    private Camera camera = new Camera();
    private Raster raster = new Raster();
    
    public final static Vector3 UX = new Vector3(1, 0, 0);
    public final static Vector3 UY = new Vector3(0, 1, 0);
    public final static Vector3 UZ = new Vector3(0, 0, 1);
    

    public Scene() {
        objects = new ArrayList<>();
        lights = new ArrayList<>();
    }

    public static Scene getScene() {
        if (scene == null) Scene.scene = new Scene();
        return scene;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
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


    public void addObject(Object object) {
        objects.add(object);
    }
}
