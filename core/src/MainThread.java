import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainThread extends JFrame implements KeyListener {
    //屏幕设置
    public static int screen_w = 1024;
    public static int screen_h = 682;
    public static int screenSize = screen_h*screen_w;
    //像素操控
    public static BufferedImage screenBuffer;
    public static int[] screen;

    MainThread(String Title){
        // 设置窗体的标题
        setTitle(Title);

        // 创建一个 JPanel 对象
        JPanel panel = new JPanel();

        // 将面板添加到窗体中
        add(panel);

        // 设置窗体的大小
        setSize(screen_w, screen_h);

        //设置窗体位置
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗体可见
        setVisible(true);

        //将屏幕像素存储在数组
        screenBuffer =  new BufferedImage(screen_w, screen_h, BufferedImage.TYPE_INT_RGB);
        DataBuffer dest = screenBuffer.getRaster().getDataBuffer();
        screen = ((DataBufferInt)dest).getData();

        Camera.init(0,0,0);

        //初始化查找表
        LookupTables.init();

        //初始化光栅渲染器
        Rasterizer.init();


        // 添加键盘事件监听器
        addKeyListener(this);

        // 获取焦点以确保键盘事件生效
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // 使用定时器每隔一定时间触发图像更新
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScreen();
                panel.getGraphics().drawImage(screenBuffer, 0, 0, MainThread.this);
            }
        });
        timer.start();
        //主循环
    }

    private void updateScreen() {
        // 操控区
        screen[0] = (163 << 16) | (216 << 8) | 239; // 天蓝色
        for (int i = 1; i < screenSize; i += i)
            System.arraycopy(screen, 0, screen, i, screenSize - i >= i ? i : screenSize - i);

        Camera.update();

        //画正方体
        for(int i =0; i < Cube.cube.length; i++) {
            Rasterizer.triangleVertices = Cube.cube[i];
            Rasterizer.triangleColor =  Cube.color[i/2];
            Rasterizer.renderType = 0;
            Rasterizer.rasterize();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainThread("哈哈");
            }
        });
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("按下键：" + e.getKeyChar());
        if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
            Camera.MOVE_FORWARD = true;
        else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S')
            Camera.MOVE_BACKWARD = true;
        else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
            Camera.SLIDE_LEFT = true;
        else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
            Camera.SLIDE_RIGHT = true;


        if(e.getKeyCode() == KeyEvent.VK_UP)
            Camera.LOOK_UP= true;
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            Camera.LOOK_DOWN = true;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            Camera.LOOK_LEFT = true;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            Camera.LOOK_RIGHT = true;

    }


    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
            Camera.MOVE_FORWARD = false;
        else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S')
            Camera.MOVE_BACKWARD = false;
        else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
            Camera.SLIDE_LEFT = false;
        else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
            Camera.SLIDE_RIGHT = false;

        if(e.getKeyCode() == KeyEvent.VK_UP)
            Camera.LOOK_UP= false;
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            Camera.LOOK_DOWN = false;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            Camera.LOOK_LEFT = false;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            Camera.LOOK_RIGHT = false;

    }

    public void keyTyped(KeyEvent e) {

    }

}



