package com.cpystu.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 球移动
 * 演示小球通过键盘控制上下左右移动
 * Java的时间控制
 *
 * @author cuipeiyao
 * @date 2025/03/11
 */
public class BallMove extends JFrame{
    MyPanel mp=null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    public BallMove(){//构造器初始化MyPanel，与MyPanel有关的（加载，大小，退出，显示等）都在这里设置
        mp=new MyPanel();
        this.add(mp);
        this.setSize(400,300);//设置的是画板MyPanel的大小
        this.addKeyListener(mp);//mp是MyPanel的对象，MyPanel类继承的JFrame对象可以监听到键盘发生的事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //面板，可以画小球
    //KeyListener 是监听器，可以监听键盘事件
    class MyPanel extends JPanel implements KeyListener {
        //为了使小球移动，给小球的坐标（x，y）设置成变量
        int x=10;
        int y=10;
        @Override
        public void paint(Graphics g) {//在MyPanel类里面重写paine方法
            super.paint(g);
            g.fillOval(x,y,20,20);//画小球(设置小球的坐标与长宽)
        }
        //ALT+回车
    //有字符输出时，该方法就会触发
        @Override
        public void keyTyped(KeyEvent e) {

        }
    //当某个键按下，该方法会触发
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println((char)e.getKeyChar()+"被按下");

            //根据用户按下的不同按键，使小球移动
            if (e.getKeyCode()==KeyEvent.VK_DOWN){
                //KeyEvent.VK_DOWN就是向下箭头对应的code值
                //java中会给每一个键分配一个值（int）
                y++;
            }else if(e.getKeyCode()==KeyEvent.VK_UP){
                y--;
            } else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                x--;
            }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                x++;
            }
            //小球已经移动，让面板重新绘制（rePaint）
            this.repaint();
        }
    //某个键松开时触发
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
