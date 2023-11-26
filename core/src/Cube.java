public class Cube {
    //做一个正方体
    static float l = 0.5f;
    static Vector3D[] vertices = {
            new Vector3D(-l, -l, -l + 2.5f),
            new Vector3D(l, -l, -l + 2.5f),
            new Vector3D(l, l, -l + 2.5f),
            new Vector3D(-l, l, -l + 2.5f),
            new Vector3D(-l, -l, l + 2.5f),
            new Vector3D(l, -l, l + 2.5f),
            new Vector3D(l, l, l + 2.5f),
            new Vector3D(-l, l, l + 2.5f)
    };
    static int[] indices = {
            0, 1, 2, 2, 3, 0,        // Front face
            1, 5, 6, 6, 2, 1,        // Right face
            5, 4, 7, 7, 6, 5,        // Back face
            4, 0, 3, 3, 7, 4,        // Left face
            3, 2, 6, 6, 7, 3,        // Top face
            4, 5, 1, 1, 0, 4         // Bottom face
    };
    static Vector3D[][] cube = {
            new Vector3D[] {vertices[2], vertices[1], vertices[0]},
            new Vector3D[] {vertices[0], vertices[3], vertices[2]},
            new Vector3D[] {vertices[6], vertices[5], vertices[1]},
            new Vector3D[] {vertices[1], vertices[2], vertices[6]},
            new Vector3D[] {vertices[7], vertices[4], vertices[5]},
            new Vector3D[] {vertices[5], vertices[6], vertices[7]},
            new Vector3D[] {vertices[3], vertices[0], vertices[4]},
            new Vector3D[] {vertices[4], vertices[7], vertices[3]},
            new Vector3D[] {vertices[6], vertices[2], vertices[3]},
            new Vector3D[] {vertices[3], vertices[7], vertices[6]},
            new Vector3D[] {vertices[1], vertices[5], vertices[4]},
            new Vector3D[] {vertices[4], vertices[0], vertices[1]}
    };
    static int[] color = {0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00, 0xFF00FF, 0x00FFFF};
}
