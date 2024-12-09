package com.cgvsu;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("3DModels/Faceform/WrapBody.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals (original): " + model.normals.size());

        // просмотр всех изначальных нормалей вершин (для отладки)
        /*System.out.println("All Original Normals:");
        for (Vector3f normal : model.normals){
            System.out.println(normal.getX() + " "+normal.getY()+" "+ normal.getZ());
        }*/
        System.out.println("Polygons: " + model.polygons.size());


        ArrayList<Vector3f> originalNormals = new ArrayList<>(model.normals);
        System.out.println("---");
        model.calculateVertexNormals();
        // просмотр всех посчитанных нормалей полигонов (для отладки)
        /*for (Polygon polygon : model.polygons){
            System.out.println(polygon.getNormal().getX() +" "+polygon.getNormal().getY() + " "+polygon.getNormal().getZ());
        }*/
        System.out.println("Normals (calculated): " + model.normals.size());
        System.out.println("All Calculated Normals:");
        if (!originalNormals.isEmpty()) {
            // проверка на совпадение с изначальными координатами нормалей вершин (если они были в файле)
            for (int i = 0; i < model.normals.size(); i++) {
                System.out.print(model.normals.get(i).getX() + " " + model.normals.get(i).getY() + " " + model.normals.get(i).getZ() + " --> ");
                System.out.println(originalNormals.get(i).equals(model.normals.get(i)));
            }
        } else {
            for (int i = 0; i < model.normals.size(); i++) {
                System.out.println(model.normals.get(i).getX() + " " + model.normals.get(i).getY() + " " + model.normals.get(i).getZ());
            }
        }
    }
}
