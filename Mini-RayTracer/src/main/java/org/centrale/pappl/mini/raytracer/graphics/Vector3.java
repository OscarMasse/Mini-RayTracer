package org.centrale.pappl.mini.raytracer.graphics;

import java.awt.*;

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

    public Vector3(Color color) {
        this(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f); }

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
    
    public Vector3 multiply (Vector3 vector) {
        return new Vector3(this.x * vector.x, this.y * vector.y, this.z * vector.z);
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

    public Vector3 clamp() {
        return new Vector3(Math.max(0, Math.min(1, x)), Math.max(0, Math.min(1, y)), Math.max(0, Math.min(1, z)));
    }

    public int toRGB() {
        return 0xFF000000 + ((int)(x * 255) << 16) + ((int) (y * 255) << 8) + ((int) (z * 255));
    }
    
    public Vector3 cross(Vector3 vector3){
        return new Vector3(this.y*vector3.z - this.z*vector3.y, this.z*vector3.x - this.x*vector3.z, this.x*vector3.y - this.y*vector3.x);
    }
}
