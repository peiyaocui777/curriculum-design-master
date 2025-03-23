package src.com.cpystu.tankgame4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import static java.lang.Thread.sleep;
//import com.cpystu.tankgame3.*;

/**
 * 我的面板
 *
 * @author cuipeiyao
 * @date 2025/03/11
 */
//为了通过WADS改变坦克的方向，使用KeyListener监听器监听键盘事件
public class MyPanel extends JPanel implements KeyListener, Runnable {//实现Runnable接口把面板也做成一个线程，
    // 实现子弹的移动效果
    //定义一个自己的坦克
    Hero hero;
    //定义一个子弹，让子弹能在面板上显示出来
    //Shot shot = null;
    //定义敌方坦克，由于敌方坦克数量多将他们放到集合里面，又因多线程这里使用Vector集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;//用变量控制敌方坦克数量
    //定义一个vector存放Bomb
    //TODO 当子弹击中坦克时把图片放进Vector里面？？
    Vector<Bomb> bombs=new Vector<>();

    //定义三个Image并初始化为空，在构造器里面给值
    Image image1=null;
    Image image2=null;
    Image image3=null;

    public MyPanel() {
        //初始化一个 子弹 在面板上//不需要，因为子弹作为坦克的属性可以通过坦克对象获取
        //再创建并初始化一个子弹会造成有多个子弹对象，调用出错
        hero = new Hero(100, 100);//初始化  我方坦克
        //shot = new Shot(hero.getX(), hero.getY(), hero.getDirect());  //或者在这里new 然后设置 hero.set shot //toDO 要么在这里 要么zaihero 类
        //创建坦克的时候设置它的速度
        //hero.setSpeed(3);默认为1
        //使用for循环初始化 敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);//得到坦克
            enemyTank.setDirect(2);//
            //给该enemyTank对象加入一颗子弹（后期可以加多颗），即在这里创建一颗子弹并设置子弹坐标
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());//此时得到了一个有坐标的shot对象
            //把得到的shot对象放到enemyThank的Vector中
            enemyTank.shots.add(shot);//??TODO ??意思是把shot对象放到shots集合再放到enemyTank里吗
            //启动线程(否则子弹坐标不会变)
            new Thread(shot).start();
            enemyTanks.add(enemyTank);//把这个坦克放到集合里面
        }
        //初始化image对象
        image1 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb1.jpg");
        image2 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb2.jpg");
        image3 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb3.jpg");
    }
    //调用paint方法绘图

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画自己坦克-封装到方法里面
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //画出子弹，绘制之前先判断一下子弹的状态，如果子弹不为空并且是存活状态再绘制
        if (hero.shot != null && hero.shot.isLive) {
            System.out.println("子弹被绘制。。。");
            g.draw3DRect(hero.shot.x, hero.shot.y, 10, 10, false);//hero.shot.x：
            //g.draw3DRect(hero.shot1.x, hero.shot1.y, 5, 5, false);//hero.shot.x：
            //hero.shot.x 因为shot的不是private,可以直接通过这个方法得到shot的坐标，
            // 如果访问控制符为私有要给shot一个get set方法，通过get(X)得到坐标
            //TODO 为什么这样得到的坐标就是炮筒位置的子弹坐标，而不是shot自己的坐标 !被改变了
        }

        //遍历画炸弹
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life>80){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life>60) {
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
                bomb.lifeDown();//生命值减少
            if (bomb.life==0){
                bombs.remove(bomb);
            }
        }

        //keyTyped()
        //drawTank(hero.getX(),hero.getY(),g,0,0);
        //for循环遍历vector画出敌人的坦克
        //拿到坦克
        for (EnemyTank enemyTank : enemyTanks) {
            //调用方法，传入参数画出敌人坦克
            //enemyTanks是所有敌方坦克的集合，zheng
            //所以获取坐标是用enemyTank
            //当敌人坦克是存活状态才绘制
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画enemyTank的时候也遍历shots,画出enemyTank的所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //TODO 先取出子弹才能绘制，不然绘制的时候没有操作对象
                    Shot shot = enemyTank.shots.get(j);
                    //画子弹前判断子弹是否存活，isLife==true再画，
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 10, 15, false);
                    } else {//子弹被销毁的时候，从Vector<Shot>里移除她
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

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

    //编写hitTank（）,实现子弹击中坦克后坦克消失的效果
    public  void hitTank(Shot s, EnemyTank enemyTank) {//传参
        switch (enemyTank.getDirect()) {//判断敌人坦克的方向，用于确定子弹进入哪个区域实现击中
            case 0:
            case 2://穿透
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                    System.out.println(enemyTank);
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //坦克被击中，bombs中放入三张图片
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    System.out.println(bomb.toString());
                    bombs.add(bomb);
                    System.out.println(bombs.size());
                }
                break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

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
            } catch (InterruptedException ignored) {


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
        while (true) {
            //休眠50毫秒
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //每隔50ms调用一次hitTank()判断子弹有没有击中坦克
            //先判断子弹是否存活，存活状态下遍历敌方的坦克，进一步判断是否击中
            if (hero.shot != null && hero.shot.isLive) {//它本身就是Boolean值 直接这样写就可以 不用判断 是否为true
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                    if (!enemyTank.isLive) {
                        enemyTanks.remove(i);
                    }
                } //这是啥意思 增强for 循环 跟for循环一个意思
                    //拿到坦克
                    //传到hitTank()

                //那三个都是一个意思 ok 怎么用的 list 集合 使用 欧克！是看着优雅 当然还有性能
                // 不都是循环那个我好理解 fori 好理解 看着明白 for 看着优雅 写 习惯了都一样 欧克 我继续了
            }
            this.repaint();
        }
    }
}


