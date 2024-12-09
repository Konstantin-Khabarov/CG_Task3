package com.cgvsu.objreader;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NormalTest {
    final float delta = 1e-7f;

    @Test
    void testNormalPolygon() {
        Vector3f v0 = new Vector3f(0, 0, 0);
        Vector3f v1 = new Vector3f(1, 0, 0);
        Vector3f v2 = new Vector3f(0, 1, 0);

        Vector3f normal = new Vector3f(0, 0, 0).normalPolygon(v0, v1, v2);
        Assertions.assertEquals(0, normal.getX(), delta);
        Assertions.assertEquals(0, normal.getY(), delta);
        Assertions.assertEquals(1, normal.getZ(), delta);
    }

    @Test
    void testCalculateVertexNormals() {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));

        ArrayList<Integer> vertexIndices = new ArrayList<>();
        vertexIndices.add(0);
        vertexIndices.add(1);
        vertexIndices.add(2);
        model.polygons.add(new Polygon());
        model.polygons.get(0).setVertexIndices(vertexIndices);

        model.calculateVertexNormals();

        Vector3f normal0 = model.normals.get(0);
        Vector3f normal1 = model.normals.get(1);
        Vector3f normal2 = model.normals.get(2);

        Assertions.assertEquals(0, normal0.getX(), delta);
        Assertions.assertEquals(0, normal0.getY(), delta);
        Assertions.assertEquals(1, normal0.getZ(), delta);

        Assertions.assertEquals(0, normal1.getX(), delta);
        Assertions.assertEquals(0, normal1.getY(), delta);
        Assertions.assertEquals(1, normal1.getZ(), delta);

        Assertions.assertEquals(0, normal2.getX(), delta);
        Assertions.assertEquals(0, normal2.getY(), delta);
        Assertions.assertEquals(1, normal2.getZ(), delta);
    }
}
