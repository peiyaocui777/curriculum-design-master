package src.com.cpystu.tankgame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 我的面板
 *
 * @author cuipeiyao
 * @date 2025/03/11
 */
//为了通过WADS改变坦克的方向，使用KeyListener监听器监听键盘事件
public class MyPanel extends JPanel implements KeyListener,Runnable {//实现Runnable接口把面板也做成一个线程，
    // 实现子弹的移动效果
    //定义一个自己的坦克
    Hero hero = null;
    //定义一个子弹，让子弹能在面板上显示出来
    //Shot shot = null;
    //定义敌方坦克，由于敌方坦克数量多将他们放到集合里面，又因多线程这里使用Vector集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;//用变量控制敌方坦克数量

    public MyPanel() {
        //初始化一个 子弹 在面板上

        hero = new Hero(100, 100);//初始化 TODO 我方坦克
        //shot = new Shot(hero.getX(), hero.getY(), hero.getDirect());  //或者在这里new 然后设置 hero.set shot //toDO 要么在这里 要么zaihero 类
        //创建坦克的时候设置它的速度
        //hero.setSpeed(3);默认为1
        //使用for循环初始化 敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);//得到坦克
            enemyTank.setDirect(2);//h
            enemyTanks.add(enemyTank);//把这个坦克放到集合里面

        }
    }
    //调用paint方法绘图

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画自己坦克-封装到方法里面
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //画出子弹，绘制之前先判断一下子弹的状态，如果子弹不为空并且是存活状态再绘制
        if (hero.shot!=null&&hero.shot.isLive==true) {
            System.out.println("子弹被绘制。。。");
            g.draw3DRect(hero.shot.x,hero.shot.y,10,10,false);//hero.shot.x：
            g.draw3DRect(hero.shot1.x,hero.shot1.y,5,5,false);//hero.shot.x：
            //hero.shot.x 因为shot的不是private,可以直接通过这个方法得到shot的坐标，
            // 如果访问控制符为私有要给shot一个get set方法，通过get(X)得到坐标
            //TODO 为什么这样得到的坐标就是炮筒位置的子弹坐标，而不是shot自己的坐标 !被改变了
        }

        //keyTyped()
        //drawTank(hero.getX(),hero.getY(),g,0,0);
        //for循环遍历vector画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);//拿到坦克
            //调用方法，传入参数画出敌人坦克
            //enemyTanks是所有敌方坦克的集合，zheng
            //所以获取坐标是用enemyTank
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
        }
        //drawTank(shot.getX(), shot.getY(), g, shot.getDirect(), 0);
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
        //根据坦克的方向，绘制对应形状的坦克
        //direct表示方向（0上 1右 2下 3 左）
        switch (direct) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//坦克上面轮
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//坦克上面轮
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);//炮筒
                break;

            default:
                System.out.println("暂时没有处理");
        }
    }

    /**
     * 输入
     *
     * @param e e
     */
    @Override
    public void keyTyped(KeyEvent e) {//e是对象名 通过它来调用方法
//有文字输入
    }

    /**
     * 按键
     *
     * @param e e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("WWWWW");
            hero.setDirect(0);//按下W键 坦克朝上
            hero.moveUp();//调用moveUp方法，坦克向上移动
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("按下A键盘");
            hero.setDirect(3);
            hero.moveLeft();
        }
        //当MyPanel的监听器监听到键盘按下J时，调用shotEnemyTank()
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("按下J键盘");
            try {
                hero.shotEnemyTank();
            } catch (InterruptedException ex) {


            }
        }
        //让面板重绘
        this.repaint();
    }

    /**
     * 密钥已释放
     *
     * @param e e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//重写Runnable的run方法
        //一个死循环，让面板一直重绘，实现子弹的移动
        while (true){
            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}


