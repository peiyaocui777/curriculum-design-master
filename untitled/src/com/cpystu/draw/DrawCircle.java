package com.cpystu.draw;

import javax.swing.*;
import java.awt.*;

/**
 * 绘制圆
 *
 * @author cuipeiyao
 * @date 2025/03/11
 */
public class DrawCircle extends JFrame {//继承JFrame（窗口）窗口嵌入面板，用Graphics g画图
    //定义一个面板
    private MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
    }

    public DrawCircle() {//构造器
        //初始化面板
        mp = new MyPanel();
        //把面板放入窗口
        this.add(mp);
        //设置窗口大小
        this.setSize(300, 400);
        //关闭窗口时程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //可视
        this.setVisible(true);
    }

    //1.先定义一个面板MyPanel,继承JPanel类 图形在面板上画
    class MyPanel extends JPanel {
        //1.MyPanel对象就是一个画板
        //2。Graphics g相当于画笔
        //3.Graphics提供了很多绘图方法
        @Override
        public void paint(Graphics g) {//绘图方法
            super.paint(g);//调用父类的方法完成初始化
            g.drawOval(10, 10, 100, 100);//画圆
        }
    }
}
