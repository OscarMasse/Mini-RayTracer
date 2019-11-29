package org.centrale.pappl.mini.raytracer.scene.object.materials;

public class Material {
    private double ks;
    private double kd;
    private double ka;
    private double alpha;

    public Material(double ks, double kd, double ka, double alpha) {
        this.ks = ks;
        this.kd = kd;
        this.ka = ka;
        this.alpha = alpha;
    }

    public double getKs() {
        return ks;
    }

    public double getKd() {
        return kd;
    }

    public double getKa() {
        return ka;
    }

    public double getAlpha() {
        return alpha;
    }
}
