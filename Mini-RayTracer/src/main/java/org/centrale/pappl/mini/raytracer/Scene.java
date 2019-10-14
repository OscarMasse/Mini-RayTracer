package org.centrale.pappl.mini.raytracer;

import java.util.ArrayList;

public class Scene {
    private static Scene scene;

    private ArrayList<Object> objects;
    private ArrayList<Light> lights;

    @SuppressWarnings("FieldMayBeFinal")
    private Camera camera = new Camera();
    @SuppressWarnings("FieldMayBeFinal")
    private Raster raster = new Raster();

    public static Scene getScene() {
        if (scene == null) Scene.scene = new Scene();
        return scene;
    }

    public void buildCameraRay() {

    }
}
