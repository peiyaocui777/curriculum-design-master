package src.com.cpystu.tankgame4;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class CpyTankGame04 extends JFrame {
    MyPanel mp = null;//这条语句要放在CpyTankGame01类里面构造器才可以直接使用
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new CpyTankGame04();
    }
//@Synchronized
    public CpyTankGame04() {
        System.out.println("请输入你的选择：1 “继续上局游戏” 2 开始新游戏");
        String key = scanner.next();
        mp = new MyPanel(key);
        //启动MyPanel线程
        Thread thread = new Thread(mp);
        //Thread thread1 = new Thread(mp);
        thread.start();
        //thread1.start();
        this.add(mp);
        this.setSize(1500, 800);
        //添加add一个监听事件，使组件能监听mp MyPanel的键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //在JFrame中增加响应关闭窗口的处理
        Recorder recorder = new Recorder();//！
        /* recorder.setEnemyTanks(mp.enemyTanks);*/
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
