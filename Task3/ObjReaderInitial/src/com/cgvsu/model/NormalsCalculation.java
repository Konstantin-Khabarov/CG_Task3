package com.cgvsu.model;

import com.cgvsu.math.Vector3f;

import java.util.ArrayList;

public class NormalsCalculation {

    // Список для хранения нормалей полигонов
    private static ArrayList<Vector3f> polygonNormals;

    // метод для расчета нормалей к полигонам
    public static void calculatePolygonNormals(Model model) {
        ArrayList<Polygon> polygons = model.polygons;
        polygonNormals = new ArrayList<>(polygons.size());

        for (int i = 0; i < polygons.size(); i++) {
            Polygon polygon = polygons.get(i);
            Vector3f v0 = model.vertices.get(polygon.getVertexIndices().get(0));
            Vector3f v1 = model.vertices.get(polygon.getVertexIndices().get(1));
            Vector3f v2 = model.vertices.get(polygon.getVertexIndices().get(2));

            polygonNormals.add(Vector3f.normalPolygon(v0, v1, v2));
        }
    }

    // метод для расчета нормалей к вершинам.
    public static void calculateVertexNormals(Model model) {
        model.normals.clear(); // на случай если модель уже содержит записи о нормалях

        calculatePolygonNormals(model); // сначала надо посчитать нормали к полигонам

        // создаем нормали вершин ("пустые")
        for (int i = 0; i < model.vertices.size(); i++) {
            model.normals.add(new Vector3f(0, 0, 0));
        }

        // суммируем нормали полигонов для каждой вершины
        int[] polygonsToVertexCount = new int[model.vertices.size()];
        for (int i = 0; i < model.polygons.size(); i++) {
            Polygon polygon = model.polygons.get(i);
            Vector3f polygonNormal = polygonNormals.get(i);

            for (int vertexIndex : polygon.getVertexIndices()) {
                model.normals.set(vertexIndex, model.normals.get(vertexIndex).add(polygonNormal));
                polygonsToVertexCount[vertexIndex]++;
            }
        }

        // находим нормали к вершинам и нормализуем их
        for (int i = 0; i < model.normals.size(); i++) {
            Vector3f normal = model.normals.get(i);
            if (polygonsToVertexCount[i] > 0) {
                normal = normal.divide(polygonsToVertexCount[i]);
            }
            normal.normalize();
            model.normals.set(i, normal);
        }
    }
}
