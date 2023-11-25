import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainThread extends JFrame {
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

        //主循环
        while (true){
            //操控区
            screen[0] = (163 << 16) | (216 << 8) | 239; //天蓝色
            for(int i = 1; i < screenSize; i+=i)
                System.arraycopy(screen, 0, screen, i, screenSize - i >= i ? i : screenSize - i);

            //把图像发送到显存
            panel.getGraphics().drawImage(screenBuffer, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        MainThread myWindow = new MainThread("哈哈");
    }
}
