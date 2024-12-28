package com.cgvsu.math;

// Это заготовка для собственной библиотеки для работы с линейной алгеброй
public class Vector3f {
    float x, y, z;
    final float eps = 1e-1f;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    // Сложение векторов
    public Vector3f add(Vector3f v) {
        return new Vector3f(x + v.x, y + v.y, z + v.z);
    }

    // Деление вектора на скаляр
    public Vector3f divide(float scalar) {
        return new Vector3f(x / scalar, y / scalar, z / scalar);
    }

    // Возвращает вектор, перпендикулярный двум переданным (векторное произведение)
    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        float vNormalX = (v1.y * v2.z) - (v1.z * v2.y);
        float vNormalY = (v1.z * v2.x) - (v1.x * v2.z);
        float vNormalZ = (v1.x * v2.y) - (v1.y * v2.x);
        return new Vector3f(vNormalX, vNormalY, vNormalZ);
    }

    // Возвращает вектор между 2мя точками
    public static Vector3f vector(Vector3f point1, Vector3f point2) {
        float vectorX = point1.x - point2.x;
        float vectorY = point1.y - point2.y;
        float vectorZ = point1.z - point2.z;
        return new Vector3f(vectorX, vectorY, vectorZ);
    }

    // Возвращает величину (длину) вектора
    public float magnitude(Vector3f v) {
        return (float) Math.sqrt((v.x * v.x) + (v.y * v.y) + (v.z * v.z));
    }

    // Возвращает нормализованный вектор (с длиной 1)
    public void normalize() {
        Vector3f v = this;
        float magnitude = magnitude(v);
        v.x /= magnitude;
        v.y /= magnitude;
        v.z /= magnitude;
    }

    // Возвращает нормаль полигона
    public static Vector3f normalPolygon(Vector3f v0, Vector3f v1, Vector3f v2) {
        Vector3f newV1 = vector(v1, v0);
        Vector3f newV2 = vector(v2, v0);

        Vector3f vNormal = cross(newV1, newV2);
        vNormal.normalize();
        return vNormal;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
