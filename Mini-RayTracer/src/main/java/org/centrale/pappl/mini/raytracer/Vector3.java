package org.centrale.pappl.mini.raytracer;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3() {
        this(0,0,0);
    }

    public Vector3(double x) {
        this(x, 0, 0);
    }

    public Vector3(double x, double y) {
        this(x, y, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 vector3) {
        this(vector3.x, vector3.y, vector3.z);
    }

    public Vector3 add(Vector3 vector3) {
        return new Vector3(x + vector3.x, y + vector3.y, z + vector3.z);
    }

    public Vector3 subtract(Vector3 vector3) {
        return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z);
    }

    public Vector3 normalized() {
        double length = Math.sqrt(dot(this));
        if(Math.abs(length) == 0) {
            return new Vector3();
        }
        return new Vector3(x/length, y/length, z/length);
    }

    public double dot(Vector3 vector3) {
        return x*vector3.x + y*vector3.y + z*vector3.z;
    }

    public Vector3 scale(double d) {
        return new Vector3(x*d, y*d, z*d);
    }

    public boolean isNullVector() {
        if (this.x == 0 && this.y == 0 && this.z == 0) return true;
        else return false;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double magnitudeSq() {
        return x*x + y*y + z*z;
    }

    public void set(Vector3 vector3) {
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
    }

}
