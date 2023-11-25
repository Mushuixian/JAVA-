import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainThread extends JFrame {
    public static int screen_w = 1024;

    public static int screen_h = 682;

    MainThread(String Title){
        // 设置窗体的标题
        setTitle(Title);

        // 创建一个 JPanel 对象
        JPanel panel = new JPanel();

        // 设置面板的背景颜色
        panel.setBackground(Color.BLUE);

        // 将面板添加到窗体中
        add(panel);

        // 设置窗体的大小
        setSize(300, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗体可见
        setVisible(true);
    }

    public static void main(String[] args) {
        MainThread myWindow = new MainThread("哈哈");
    }
}
