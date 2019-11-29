package org.centrale.pappl.mini.raytracer.graphics;

import org.centrale.pappl.mini.raytracer.scene.object.Box;

public class Matrix3 {

    private Vector3 column1;
    private Vector3 column2;
    private Vector3 column3;

    public Matrix3() {
        this(new Vector3(), new Vector3(), new Vector3());
    }

    public Matrix3(Vector3 v1, Vector3 v2, Vector3 v3) {
        this.column1 = new Vector3(v1);
        this.column2 = new Vector3(v2);
        this.column3 = new Vector3(v3);
    }

    public Matrix3(Matrix3 m) {
        this.column1 = new Vector3(m.getColumn1());
        this.column2 = new Vector3(m.getColumn2());
        this.column3 = new Vector3(m.getColumn3());
    }

    public Vector3 getColumn1() {
        return column1;
    }

    public Vector3 getColumn2() {
        return column2;
    }

    public Vector3 getColumn3() {
        return column3;
    }

    public static Matrix3 getIdentity3() {
        return new Matrix3(new Vector3(1, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 0, 1));
    }

    public Matrix3 translate(Vector3 vector3) {
        return new Matrix3(
                new Vector3(1),
                new Vector3(0, 1),
                new Vector3(vector3));
    }

    public Matrix3 translate(float x, float y, float z) {
        return new Matrix3(
                new Vector3(1),
                new Vector3(0, 1),
                new Vector3(x, y, z));
    }

    public Matrix3 rotate(Vector3 axis, double angle) {
        final double rad = (angle / 180.0f) * Math.PI;
        final double cosa = Math.cos(rad);
        final double sina = Math.sin(rad);
        final Vector3 naxis = axis.normalized();
        final double rx = naxis.x;
        final double ry = naxis.y;
        final double rz = naxis.z;
        final double icosa = 1 - cosa;

        return new Matrix3(
                new Vector3(
                        icosa * rx * rx + cosa,
                        icosa * rx * ry + rz * sina,
                        icosa * rx * rz - ry * sina),

                new Vector3(
                        icosa * rx * ry - rz * sina,
                        icosa * ry * ry + cosa,
                        icosa * ry * rz + rx * sina),

                new Vector3(
                        icosa * rx * rz + ry * sina,
                        icosa * ry * rz - rx * sina,
                        icosa * rz * rz + cosa));
    }

    public Vector3 multiply(Vector3 v) {
        return new Vector3(
                column1.x * v.x  + column2.x * v.y + column3.x * v.z,
                column1.y * v.y  + column2.y * v.y + column3.y * v.z,
                column1.z * v.z  + column2.z * v.y + column3.z * v.z);
    }

    public Matrix3 transpose() {
        return new Matrix3(
                new Vector3(
                        column1.x,
                        column2.x,
                        column3.x),
                new Vector3(
                        column1.y,
                        column2.y,
                        column3.y),
                new Vector3(
                        column1.z,
                        column2.z,
                        column3.z));
    }
}
