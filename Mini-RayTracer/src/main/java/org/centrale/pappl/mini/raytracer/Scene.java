package org.centrale.pappl.mini.raytracer;

import java.util.ArrayList;

public class Scene {
    private static Scene scene;

    private ArrayList<Object> objects;
    private ArrayList<Light> lights;

    private Camera camera = new Camera();
    private Raster raster = new Raster();

    public Scene() {
        //TODO ...
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
    

}
