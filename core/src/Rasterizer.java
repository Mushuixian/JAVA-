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
    public static void init(){
    //初始化三角形变换后的顶点
        updatedVertices = new Vector3D[]{
                new Vector3D(0,0,0),
                new Vector3D(0,0,0),
                new Vector3D(0,0,0),
        };
    }
    //光栅入口
    public static void rasterize(){
        //变换三角形顶点

        //扫描三角形

        //给三角形着色

    }

    public static void transformationVertices(){

    }

}
