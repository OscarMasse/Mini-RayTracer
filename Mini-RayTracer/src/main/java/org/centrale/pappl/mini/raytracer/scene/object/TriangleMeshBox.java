/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pappl.mini.raytracer.scene.object;

import org.centrale.pappl.mini.raytracer.graphics.Ray;
import org.centrale.pappl.mini.raytracer.graphics.RayCastResult;
import org.centrale.pappl.mini.raytracer.graphics.Vector3;

/**
 * Non-Axis Aligned Box made up of a mesh of 12 triangles
 * @author Oscar Masse & Sarah Petrocchi @ECN
 */
public class TriangleMeshBox extends SceneObject {

    //ATTRIBUTES
    /**
     * Closest Vertex
     */
    public Vector3 closeVertex;

    /**
     * Farthest Vextex
     */
    public Vector3 farVertex;

    /**
     * Box' x axis
     */
    public Vector3 ux;

    /**
     * Box' y axis
     */
    public Vector3 uy;

    /**
     * Box' z axis
     */
    public Vector3 uz;

    /**
     * List of all 8 vertices
     */
    public Vector3[] vertices;

    /**
     * Triangle that was hit on intersection
     */
    public Triangle hitTriangle;

    //CONSTRUCTORS
    /**
     *
     * @param closeVertex
     * @param farVertex
     * @param ux
     * @param uy
     * @param uz
     */
    public TriangleMeshBox(Vector3 closeVertex, Vector3 farVertex, Vector3 ux, Vector3 uy, Vector3 uz) {
        this.closeVertex = closeVertex;
        this.farVertex = farVertex;
        this.ux = ux;
        this.uy = uy;
        this.uz = uz;
    }

    //OTHER METHODS
    /**
     * Computes all 8 vertices from both vertices and 3 directional vectors
     */
    public void computeVertices() {
        this.vertices = new Vector3[8];
        double a = farVertex.x - closeVertex.x;
        double b = farVertex.y - closeVertex.y;
        double c = farVertex.z - closeVertex.z;
        vertices[0] = closeVertex;
        vertices[1] = new Vector3(closeVertex.add(ux.scale(a)));
        vertices[2] = new Vector3(closeVertex.add(ux.scale(a).add(uy.scale(b))));
        vertices[3] = new Vector3(closeVertex.add(uy.scale(b)));
        vertices[4] = new Vector3(farVertex.add(ux.scale(-a).add(uy.scale(-b))));
        vertices[5] = new Vector3(farVertex.add(uy.scale(-b)));
        vertices[6] = farVertex;
        vertices[7] = new Vector3(farVertex.add(ux.scale(-a)));
    }

    /**
     * Computes all triangles in the mesh from the 12 vertices
     * @return
     */
    public Triangle[] computeTriangles() {
        Triangle[] triangles = new Triangle[12];
        this.computeVertices();

        triangles[0] = new Triangle(vertices[4], vertices[7], vertices[3]);
        triangles[1] = new Triangle(vertices[4], vertices[0], vertices[3]);
        triangles[2] = new Triangle(vertices[3], vertices[6], vertices[7]);
        triangles[3] = new Triangle(vertices[2], vertices[6], vertices[3]);

        triangles[4] = new Triangle(vertices[0], vertices[3], vertices[2]);
        triangles[5] = new Triangle(vertices[0], vertices[1], vertices[2]);
        triangles[6] = new Triangle(vertices[4], vertices[0], vertices[1]);
        triangles[7] = new Triangle(vertices[4], vertices[5], vertices[1]);

        triangles[8] = new Triangle(vertices[1], vertices[2], vertices[6]);
        triangles[9] = new Triangle(vertices[1], vertices[5], vertices[6]);
        triangles[10] = new Triangle(vertices[5], vertices[6], vertices[7]);
        triangles[11] = new Triangle(vertices[5], vertices[4], vertices[7]);
        return triangles;
    }

    /**
     * Computes intersection of the ray with this object
     * @param ray
     * @return
     */
    public RayCastResult intersect(Ray ray) {
        RayCastResult hitRayCastResult = new RayCastResult();

        Triangle[] triangles = computeTriangles();

        double shortestDistance = Double.MAX_VALUE;

        for (int i = 0; i < triangles.length; i++) {
            RayCastResult rayCastResult = triangles[i].intersect(ray);
            if (rayCastResult.hit) {
                double objectDistance = rayCastResult.intersection.magnitude();
                if (objectDistance < shortestDistance) {
                    shortestDistance = objectDistance;
                    hitRayCastResult = rayCastResult;
                }
            }
        }
        if (hitRayCastResult.hit) {
            this.hitTriangle = ((Triangle) hitRayCastResult.hitObject);
            hitRayCastResult.hitObject = this;
        }
        return hitRayCastResult;
    }

    /**
     * Computes normal vector for the position on this object
     * @param position
     * @return
     */
    public Vector3 getNormal(Vector3 position) {
        return this.hitTriangle.getNormal(position);
    }
}
