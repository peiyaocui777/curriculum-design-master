package src.com.cpystu.tankgame3;



import javax.swing.*;

public class CpyTankGame02 extends JFrame {
    MyPanel mp = null;//这条语句要放在CpyTankGame01类里面构造器才可以直接使用

    public static void main(String[] args) {
        new CpyTankGame02();
    }

    public CpyTankGame02() {
        mp = new MyPanel();
        //启动MyPanel线程
        Thread thread = new Thread(mp);
        Thread thread1 = new Thread(mp);
        thread.start();
        thread1.start();
        this.add(mp);
        this.setSize(1000, 750);
        //添加add一个监听事件，使组件能监听mp MyPanel的键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
