package org.centrale.pappl.mini.raytracer;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(double x) {
        this.x = x;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 vector3) {
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
    }

    public void add(Vector3 vector3) {
        this.x += vector3.x;
        this.y += vector3.y;
        this.z += vector3.z;
    }

    public Vector3 substract(Vector3 vector3) {
        return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z);
    }

    public Vector3 normalize() {
        double lenght = Math.sqrt(scalarProduct(this));
        if(Math.abs(lenght) == 0) {
            return new Vector3();
        }
        return new Vector3(x/lenght, y/lenght, z/lenght);
    }

    public double scalarProduct(Vector3 vector3) {
        return x*vector3.x + y*vector3.y + z*vector3.z;
    }

    public Vector3 scalarMultiplication(double d) {
        return new Vector3(x*d, y*d, z*d);
    }

}
