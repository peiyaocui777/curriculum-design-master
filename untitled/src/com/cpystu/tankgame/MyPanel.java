package com.cpystu.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * 我的面板
 *
 * @author cuipeiyao
 * @date 2025/03/11
 */
public class MyPanel extends JPanel {
    //定义一个坦克
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化
    }
    //调用paint方法绘图

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画坦克-封装到方法里面
        drawTank(hero.getX(),hero.getY(),g,0,0);
        //drawTank(hero.getX(),hero.getY(),g,0,0);
    }

    /**
     * 提取
     *
     * @param x      x 坦克的左上角横坐标
     * @param y      y 纵坐标
     * @param g      g 画笔
     * @param direct 方向
     * @param type   类型 （敌我）
     *///编写方法 画出坦克
    /*
     * */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型的坦克，设置不同的颜色
        switch (type) {
            case 0://我方坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌方坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向，绘制坦克
        switch (direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//坦克左轮
                g.fill3DRect(x+30,y,10,60,false);//坦克右轮
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10,y+20,20,20);//圆盖
                g.drawLine(x+20,y+30,x+20,y);//炮筒
                break;
          default:
                System.out.println("暂时没有处理");
        }
    }
}


