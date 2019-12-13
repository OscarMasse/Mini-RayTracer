package org.centrale.pappl.mini.raytracer.scene.object.materials;

/**
 * Material computed with the Phong model
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Material {
    
    //ATTRIBUTES
    
    /**
     * Specular reflection constant 
     */
    private double ks;
    
    /**
     * Diffuse reflection constant
     */
    private double kd;
    
    /**
     * Ambient reflection constant
     */
    private double ka;
    
    /**
     * Shininess reflection constant
     */
    private double alpha;
    private double absorbance;
    private double reflectance;
    private double transmittance;

    //CONSTRUCTORS
    /**
     *
     * @param ks Specular coefficient
     * @param kd Diffusion coefficient
     * @param ka Ambient coefficient
     * @param alpha Glossiness coefficient
     */
    public Material(double ks, double kd, double ka, double alpha) {
        this.ks = ks;
        this.kd = kd;
        this.ka = ka;
        this.alpha = alpha;
        this.absorbance = 1;
        this.reflectance = 0;
        this.transmittance = 0;
    }

    public void setParameters(double absorbance, double reflectance, double transmittance) {
        this.absorbance = absorbance;
        this.reflectance = reflectance;
        this.transmittance = transmittance;
    }

    //GETTERS AND SETTERS
    /**
     *
     * @return
     */
    public double getKs() {
        return ks;
    }

    /**
     *
     * @return
     */
    public double getKd() {
        return kd;
    }

    /**
     *
     * @return
     */
    public double getKa() {
        return ka;
    }

    /**
     *
     * @return
     */
    public double getAlpha() {
        return alpha;
    }

    public double getAbsorbance() {
        return absorbance;
    }

    public double getReflectance() {
        return reflectance;
    }

    public double getTransmittance() {
        return transmittance;
    }
}
