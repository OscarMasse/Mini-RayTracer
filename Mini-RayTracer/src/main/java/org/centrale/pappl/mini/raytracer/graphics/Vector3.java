package org.centrale.pappl.mini.raytracer.graphics;

import java.awt.*;

/**
 * Vector3 class
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class Vector3 {

    //ATTRIBUTES
    /**
     * x Coordinate
     */
    public double x;

    /**
     * y Coordinate
     */
    public double y;

    /**
     * z Coordinate
     */
    public double z;

    //CONSTRUCTORS
    /**
     *
     */
    public Vector3() {
        this(0,0,0);
    }

    /**
     *
     * @param x
     */
    public Vector3(double x) {
        this(x, 0, 0);
    }

    /**
     *
     * @param x
     * @param y
     */
    public Vector3(double x, double y) {
        this(x, y, 0);
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @param vector3
     */
    public Vector3(Vector3 vector3) {
        this(vector3.x, vector3.y, vector3.z);
    }

    /**
     *
     * @param color
     */
    public Vector3(Color color) {
        this(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f); }

    //OTHER METHODS
    /**
     * Sum of two vectors
     * @param vector3
     * @return
     */
    public Vector3 add(Vector3 vector3) {
        return new Vector3(x + vector3.x, y + vector3.y, z + vector3.z);
    }

    /**
     * Substraction of 2 vectors
     * @param vector3
     * @return
     */
    public Vector3 subtract(Vector3 vector3) {
        return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z);
    }

    /**
     * Normalizes a vector
     * @return
     */
    public Vector3 normalized() {
        double length = Math.sqrt(dot(this));
        if(Math.abs(length) == 0) {
            return new Vector3();
        }
        return new Vector3(x/length, y/length, z/length);
    }

    /**
     * Dot product of two vectors
     * @param vector3
     * @return
     */
    public double dot(Vector3 vector3) {
        return x*vector3.x + y*vector3.y + z*vector3.z;
    }
    
    /**
     * Cross product of two vectors
     * @param vector3
     * @return
     */
    public Vector3 cross(Vector3 vector3){
        return new Vector3(this.y*vector3.z - this.z*vector3.y, this.z*vector3.x - this.x*vector3.z, this.x*vector3.y - this.y*vector3.x);
    }

    /**
     * Scales a vector by a double
     * @param d
     * @return
     */
    public Vector3 scale(double d) {
        return new Vector3(x*d, y*d, z*d);
    }
    
    /**
     * Multiplies both vectors' coordinates
     * @param vector
     * @return
     */
    public Vector3 multiply (Vector3 vector) {
        return new Vector3(this.x * vector.x, this.y * vector.y, this.z * vector.z);
    }

    /**
     * Checks if a vector is null
     * @return
     */
    public boolean isNullVector() {
        if (this.x == 0 && this.y == 0 && this.z == 0) return true;
        else return false;
    }

    /**
     * Computes a vector's magnitude
     * @return
     */
    public double magnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Computes the square of a vector's magnitude
     * @return
     */
    public double magnitudeSq() {
        return x*x + y*y + z*z;
    }

    /**
     * Ensures illumination doesn't go beyond set limits (0 and 1)
     * @return
     */
    public Vector3 clamp() {
        return new Vector3(Math.max(0, Math.min(1, x)), Math.max(0, Math.min(1, y)), Math.max(0, Math.min(1, z)));
    }

    /**
     * Converts vector coordinates to RGB color
     * @return
     */
    public int toRGB() {
        return 0xFF000000 + ((int)(x * 255) << 16) + ((int) (y * 255) << 8) + ((int) (z * 255));
    }

    @Override
    public String toString() {
        return "(" + x + " ," + y + " ," + z + ")";
    }
}
