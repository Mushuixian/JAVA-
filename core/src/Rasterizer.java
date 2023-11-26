//三角形渲染
public class Rasterizer {
    //设置屏幕分辨率
    public static int screen_w = MainThread.screen_w;
    public static int screen_h = MainThread.screen_h;

    //屏幕像素组
    public static int[] screen = MainThread.screen;

    //视角的原点到屏幕的距离
    public static int screenDistance = 815;

    //未经变换三角形的顶点
    public static Vector3D[] triangleVertices;

    //变换后三角形的顶点
    public static Vector3D[] updatedVertices;

    //三角形的顶点数
    public static int verticesCount = 3;

    //三角形变换后的顶点投影在屏幕上的2D坐标
    public static float[][] vertices2D = new float[4][2];

    //遍历后的包含三角形的数组
    public static float[][] BoudingBox = new float[2][2];

    //三角形的颜色
    public static int triangleColor;

    //三角形的类型
    public static int renderType;

    //三角形所在对象本身坐标系进行的平移变换
    public static Vector3D localTranslation;

    //三角形所在对象本身坐标系的旋转变换
    public static int localRotationX, localRotationY, localRotationZ;

    //初始化光栅
    public static void init() {
        //初始化三角形变换后的顶点
        updatedVertices = new Vector3D[]{
                new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0),
        };
    }

    //光栅入口
    public static void rasterize() {
        //变换三角形顶点
        transformationVertices();
        //投影三角形
        project();
        //扫描三角形
        scan();
        //给三角形着色

    }

    //变换三角形顶点
    public static void transformationVertices() {
        //根据相机改变三角形位置
        //把三角形的原有顶点按视角变换的反方向用来变换
        float x = 0,y = 0, z = 0,
                sinY = LookupTables.sin[Camera.Y_angle],
                cosY = LookupTables.cos[Camera.Y_angle],
                sinX = LookupTables.sin[Camera.X_angle],
                cosX = LookupTables.cos[Camera.X_angle];
        for(int i = 0; i < 3; i++){
            //将每个顶点沿着视角的x、y、z轴移动，
            x = triangleVertices[i].x - Camera.position.x;
            y = triangleVertices[i].y - Camera.position.y;
            z = triangleVertices[i].z - Camera.position.z;

            //用矢量旋转公式对顶点进行旋转变换
            updatedVertices[i].x = cosY*x - sinY*z;
            updatedVertices[i].z = sinY*x + cosY*z;

            z = updatedVertices[i].z;

            updatedVertices[i].y = cosX*y - sinX*z;
            updatedVertices[i].z = sinX*y + cosX*z;
        }
        }


    //投影三角形
    public static void project() {
        for (int i = 0; i < verticesCount; i++) {
            // 避免除以零错误
            if (updatedVertices[i].z != 0) {
                vertices2D[i][0] = (float) screen_w / 2 + updatedVertices[i].x * screenDistance / updatedVertices[i].z;
                vertices2D[i][1] = (float) screen_h / 2 - updatedVertices[i].y * screenDistance / updatedVertices[i].z;
            }
        }
    }

        // 扫描四边形
        public static void scan() {
            // 求包围四边形的矩形
            float up, down, right, left;

            // 计算矩形边界
            right = Math.max(vertices2D[0][0], Math.max(vertices2D[1][0], Math.max(vertices2D[2][0], vertices2D[3][0])));
            left = Math.min(vertices2D[0][0], Math.min(vertices2D[1][0], Math.min(vertices2D[2][0], vertices2D[3][0])));
            up = Math.max(vertices2D[0][1], Math.max(vertices2D[1][1], Math.max(vertices2D[2][1], vertices2D[3][1])));
            down = Math.min(vertices2D[0][1], Math.min(vertices2D[1][1], Math.min(vertices2D[2][1], vertices2D[3][1])));

            // 确保边界在屏幕范围内
            right = Math.min(right, screen_w - 1);
            left = Math.max(left, 0);
            up = Math.min(up, screen_h - 1);
            down = Math.max(down, 0);

            // 遍历矩形内的每个像素
            for (int y = (int) down; y <= up; y++) {
                for (int x = (int) left; x <= right; x++) {
                    int pixelIndex = y * screen_w + x;

                    // 判断像素点是否在四边形内
                    if (ifInside(pixelIndex)) {
                        // 在四边形内，可以进行染色等操作
                        screen[pixelIndex] = triangleColor;
                    }
                }
            }
        }


    // 判断像素点是否在三角形内
    // 判断像素点是否在三角形内
    public static Boolean ifInside(int p) {
        // 计算像素的坐标
        int pointy = p / screen_w;
        int pointx = p % screen_w;

        // 得到三角形的边
        float[][] line = new float[3][2];
        for (int i = 0; i < verticesCount; i++) {
            int nextIndex = (i + 1) % verticesCount; // 循环获取下一个顶点的索引
            line[i][0] = vertices2D[nextIndex][0] - vertices2D[i][0];
            line[i][1] = vertices2D[nextIndex][1] - vertices2D[i][1];
        }

        // 计算叉积
        for (int i = 0; i < verticesCount; i++) {
            float templineX = pointx - vertices2D[i][0];
            float templineY = pointy - vertices2D[i][1];
            float result = line[i][0] * templineY - line[i][1] * templineX;

            if (result < 0) {
                return false;
            }
        }

        return true;
    }




    //染色

}
