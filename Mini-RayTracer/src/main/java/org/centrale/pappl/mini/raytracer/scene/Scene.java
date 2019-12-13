package org.centrale.pappl.mini.raytracer.scene;

import org.centrale.pappl.mini.raytracer.scene.light.AmbientLight;
import org.centrale.pappl.mini.raytracer.scene.light.Light;
import org.centrale.pappl.mini.raytracer.scene.object.SceneObject;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;
import java.util.ArrayList;

/**
 *
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Scene {
    
    //ATTRIBUTES
    
    /**
     * Scene
     */
    private static Scene scene;

    /**
     * List of objects in the scene
     */
    private ArrayList<SceneObject> sceneObjects;
    
    /**
     * List of lights in the scene
     */
    private ArrayList<Light> lights;

    /**
     * Scene's camera
     */
    private Camera camera = Camera.getCamera();
    
    /**
     * Scene's raster
     */
    private Raster raster = new Raster();
    
    /**
     * Scene's ambient light
     */
    private AmbientLight ambientLight;

    /**
     * Scene's x axis vector
     */
    public final static Vector3 UX = new Vector3(1, 0, 0);

    /**
     * Scene's y axis vector
     */
    public final static Vector3 UY = new Vector3(0, 1, 0);

    /**
     * Scene's z axis vector
     */
    public final static Vector3 UZ = new Vector3(0, 0, 1);


    //CONSTRUCTORS
    
    private Scene() {
        sceneObjects = new ArrayList<>();
        lights = new ArrayList<>();
        ambientLight = new AmbientLight();
    }

    //GETTERS AND SETTERS
    
    /**
     *
     * @return
     */
    public static Scene getScene() {
        if (scene == null) Scene.scene = new Scene();
        return scene;
    }

    /**
     *
     * @return
     */
    public ArrayList<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    /**
     *
     * @param sceneObjects
     */
    public void setSceneObjects(ArrayList<SceneObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    /**
     *
     * @return
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     *
     * @param lights
     */
    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    /**
     *
     * @return
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     *
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     *
     * @return
     */
    public Raster getRaster() {
        return raster;
    }

    /**
     *
     * @param raster
     */
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    /**
     *
     * @param sceneObject
     */
    public void addObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    /**
     *
     * @param light
     */
    public void addLight(Light light) {
        lights.add(light);
    }

    /**
     *
     * @return
     */
    public Light getAmbientLight() {
        return this.ambientLight;
    }

    /**
     *
     * @param ambientLight
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }
}

