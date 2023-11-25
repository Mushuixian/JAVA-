public class Camera {
    //相机位置
    public Vector3D position;

    //相机方向
    public Vector3D direction;

    //上方向
    public Vector3D upDirection = new Vector3D(0,1,0);

    //判断视角走位，观察方向的变量
    public static boolean MOVE_FORWARD, MOVE_BACKWARD, SLIDE_LEFT, SLIDE_RIGHT, LOOK_UP, LOOK_DOWN, LOOK_RIGHT, LOOK_LEFT;

    //视角在Y轴的旋转（向左看或向右看）
    public static int Y_angle;

    //视角在X轴上的旋转（向上看或向下看）
    public static  int X_angle;

    //视角改变方向速率
    public static int turnRate = 2;

    //视角改变位置速度
    public static float moveSpeed = 0.03f;

    //初始化
    Camera(float x,float y,float z){
        position = new Vector3D(x,y,z);
        direction = new Vector3D(0,0,-1);
    }

    public void update(){
        //向上看
        if(LOOK_UP){
            X_angle += turnRate;
            if(X_angle > 89 && X_angle < 100)
                X_angle = 89;
        }

        //向下看
        if(LOOK_DOWN){
            X_angle -= turnRate;
            if(X_angle > 271 && X_angle < 180)
                X_angle = -89;
        }

        //向右看
        if(LOOK_RIGHT){
            Y_angle += turnRate;
        }

        //向左看
        if(LOOK_LEFT){
            Y_angle -= turnRate;
        }

        //角度在360以内
        Y_angle = (Y_angle + 360) % 360;
        X_angle = (X_angle + 360) % 360;

        //更新相机朝向
        direction.rotate_X(X_angle);
        direction.rotate_Y(Y_angle);

        //向前走
        if(MOVE_FORWARD)
            position.add(direction,moveSpeed);

        //向后走
        if (MOVE_BACKWARD)
            position.subtract(direction,moveSpeed);

        //向左走
        if (SLIDE_LEFT){
            Vector3D left = upDirection.cross(direction);
            left.unit();
            position.add(left);
        }
        //向右走
        if (SLIDE_RIGHT){
            Vector3D right = direction.cross(upDirection);
            right.unit();
            position.add(right);
        }
    }

}
