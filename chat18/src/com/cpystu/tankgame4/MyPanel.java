package src.com.cpystu.tankgame4;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
    //定义一个Node集合，存放通过Recorder.getNodesAndEnemyTankRec（）传进来的vector,用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    int enemyTankSize = 3;//用变量控制敌方坦克数量
    //定义一个vector存放Bomb
    //TODO 当子弹击中坦克时把图片放进Vector里面？？ 是
    Vector<Bomb> bombs=new Vector<>();

    //定义三个Image并初始化为空，在构造器里面给值
    Image image1=null;
    Image image2=null;
    Image image3=null;

    public MyPanel(String key) {
        File file = new File(Recorder.getRecordeFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTanksRec(); //需要判断 然后修改 如果是新游戏 就不要读取 刚刚为什么不行  是因为发现 整个流程都要改
        } else {
            System.out.println("请先进行游戏！");
            key = "2";
        }
        Recorder.setEnemyTanks(enemyTanks);//这里用的是静态方法 //todo 为什么不能new一个Recorder对象再调用他的setEnemyTanks()
        hero = new Hero(500, 100);//初始化  我方坦克
        //shot = new Shot(hero.getX(), hero.getY(), hero.getDirect());  //或者在这里new 然后设置 hero.set shot //toDO 要么在这里 要么zaihero 类
        //创建坦克的时候设置它的速度
        //hero.setSpeed(3);默认为1
        switch (key) {
            case "1":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());//得到坦克
                    //todo 使enemyTank持有enemyTanks集合，即能够得到enemyTanks的对象 不是很理解
                    enemyTank.setEnemyTanks(enemyTanks);//对象名enemyTank指的是enemyTank类的对象，调用该类的setEnemyTanks（），就将MyPanel的集合传了过去
                    enemyTank.setDirect(node.getDirect());//
                    new Thread(enemyTank).start();//创建敌人坦克时启动线程
                    //给该enemyTank对象加入一颗子弹（后期可以加多颗），即在这里创建一颗子弹并设置子弹坐标
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());//此时得到了一个有坐标的shot对象
                    //把得到的shot对象放到enemyThank的Vector中
                    enemyTank.shots.add(shot);//??TODO ??意思是把shot对象放到shots集合再放到enemyTank里吗
                    //启动线程(否则子弹坐标不会变)
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);//把这个坦克放到集合里面
                }
                break;
            case "2": //看代码 选择 2的时候 也没有加载呀
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);//得到坦克
                    //todo 使enemyTank持有enemyTanks集合，即能够得到enemyTanks的对象 不是很理解
                    enemyTank.setEnemyTanks(enemyTanks);//对象名enemyTank指的是enemyTank类的对象，调用该类的setEnemyTanks（），就将MyPanel的集合传了过去
                    enemyTank.setDirect(2);//
                    new Thread(enemyTank).start();//创建敌人坦克时启动线程
                    //给该enemyTank对象加入一颗子弹（后期可以加多颗），即在这里创建一颗子弹并设置子弹坐标
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());//此时得到了一个有坐标的shot对象
                    //把得到的shot对象放到enemyThank的Vector中
                    enemyTank.shots.add(shot);//??TODO ??意思是把shot对象放到shots集合再放到enemyTank里吗
                    //启动线程(否则子弹坐标不会变)
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);//把这个坦克放到集合里面
                }
                break;
            default:
                System.out.println("输入有误！");
                break;
        }
        //使用for循环初始化 敌人的坦克
        //初始化image对象
        image1 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb1.jpg");
        image2 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb2.jpg");
        image3 = Toolkit.getDefaultToolkit().getImage("D:\\project\\curriculum-design-master\\chat18\\out\\production\\untitled\\bomb3.jpg");
//        Recorder recorder = new Recorder();//在MyPanel类里面创建Recorder类的对象，对象名为recorder
//        recorder.setEnemyTanks(enemyTanks);//通过对象名调用recorder的set方法，将MyPanel的enemyTanks传给Recorder
        //初始化一个 子弹 在面板上//不需要，因为子弹作为坦克的属性可以通过坦克对象获取
        //再创建并初始化一个子弹会造成有多个子弹对象，调用出错
    }
    //编写一个方法，使得界面上显示出当前击毁敌人坦克数的信息
    public void showInfo(Graphics g){//需要在面板上画出来，传入参数Graphics g
        //画出“玩家总成绩”
        g.setColor(Color.black);//字体的颜色
        Font font = new Font("宋体", Font.BOLD, 25);//Font.BOLD 粗体
        //把字体设置给画笔
        g.setFont(font);
        //画一个字符串
        g.drawString("您累计击毁敌方坦克：",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.black);//重置画笔颜色
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);//todo Recorder.getAllEnemyTankNum()+""把int转成字符串，变量接收
    }
    //调用paint方法绘图

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //调用showInfo()画出信息
        showInfo(g);
        //画自己坦克-封装到方法里面
        //我方坦克hero不为空且存活状态下draw
        if (hero != null && hero.isLive) {//TODO 问题1：为什么这个地方需要判断hero是否为空，hitHero（）里不判断
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);//TODO 问题2：hero被击中后不需要remove吗 怎么消失的
        }
        //画出子弹，绘制之前先判断一下子弹的状态，如果子弹不为空并且是存活状态再绘制
        //遍历vector集合，取出所有子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
            //System.out.println("子弹被绘制。。。");
            g.draw3DRect(shot.x, shot.y, 2, 2, false);//hero.shot.x：
            //g.draw3DRect(hero.shot1.x, hero.shot1.y, 5, 5, false);//hero.shot.x：
            //hero.shot.x 因为shot的不是private,可以直接通过这个方法得到shot的坐标，
            // 如果访问控制符为私有要给shot一个get set方法，通过get(X)得到坐标
            //TODO 为什么这样得到的坐标就是炮筒位置的子弹坐标，而不是shot自己的坐标 !被改变了
        }else{//如果子弹已经无效 就从vector集合中拿掉
               hero.shots.remove(shot);
            }
        }

        //遍历画炸弹
        for (int i = 0; i < bombs.size(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombs.get(i);
            if (bomb.life>6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life>3) {
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
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
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
    public void hitTank(Shot s, Tank Tank) {//传参 TODO 这个方法实现的是子弹是否击中坦克，击中了就触发爆炸效果
        //TODO 不论是enemyTank还是hero,可以把形参
        //TODO 改成enemyTank与hero的父类Tank，这样就不用重新再写一个方法判断hero有没有被击中了
        switch (Tank.getDirect()) {//判断敌人坦克的方向，用于确定子弹进入哪个区域实现击中
            case 0:
            case 2://穿透
                if (s.x > Tank.getX() && s.x < Tank.getX() + 40 && s.y > Tank.getY() && s.y < Tank.getY() + 60) {
                    System.out.println(Tank);
                    s.isLive = false;
                    Tank.isLive = false;
                    //坦克被击中，bombs中放入三张图片
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    System.out.println(bomb.toString());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 60 && s.y > Tank.getY() && s.y < Tank.getY() + 40) {
                    s.isLive = false;
                    Tank.isLive = false;
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                }
                break;

        }
    }

    //编写一个方法，判断敌方坦克的子弹是否击中我方hero
    public void hitHero() {
        //遍历敌方的enemyTanks集合
        for (int i = 0; i < enemyTanks.size(); i++) {
            //得到一个坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank的shots集合
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //得到1颗子弹
                Shot shot = enemyTank.shots.get(j);
                //调用hitTank()判断这颗子弹有没有进入到我方坦克区域，进入区域就触发爆炸效果
                if (hero.isLive && shot.isLive) {//判断条件：hero是存活状态，且子弹存活 TODO hero中没有isLive的属性，直接把这个属性放在父类Tank中
                    hitTank(shot, hero);
                }
            }
        }
    }
    //写一个方法，遍历我方所有的子弹，敌方所有的坦克，确保每颗子弹都能有效击中
    public void hitEnemyTank(){
        //遍历得到我方子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            //判断我方子弹是否为空及存活状态
            if (shot!=null&&shot.isLive){
                //遍历得到敌方坦克
                for (int j=0;j<enemyTanks.size();j++){
                    EnemyTank enemyTank = enemyTanks.get(j);
                    //判断enemyTank
                    if (enemyTank!=null&&enemyTank.isLive){
                        hitTank(shot,enemyTank);
                        //Recorder.addAllEnemyTankNum();要放在remove（）语句后面
                        //Record类中的addAllEnemyTankNum方法是static静态方法，可以不new,通过类名直接调用
                        if (!enemyTank.isLive) {
                            enemyTanks.remove(enemyTank);
                            Recorder.addAllEnemyTankNum();
                            /*//todo 试一下积分加加放在这里可以不 没有效果 为什么
                        总结：todo 没有效果是因为showInfo()方法里面的   g.drawString（）中的参数没有用变量接收
                            Recorder.addAllEnemyTankNum();放在这里，相较于放在hitTank()中
                            不需要先判断他的运行类型是 enemyTank再执行该语句
                            */
                        }
                    }
                }
            }
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
    @SneakyThrows
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("WWWWW");
            hero.setDirect(0);//按下W键 坦克朝上
            if (hero.getY()>0) {//使用hero.get(x)
                hero.moveUp();//调用moveUp方法，坦克向上移动
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX()+60<1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY()+60<750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("按下A键盘");
            hero.setDirect(3);
            if (hero.getX()>0) {
                hero.moveLeft();
            }
        }
        //当MyPanel的监听器监听到键盘按下J时，调用shotEnemyTank()
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("按下J键盘");
            //实现了：发射一颗子弹时 当子弹为空或者子弹已经销毁时才能再次发射子弹
            /*if (hero.shot==null||!hero.shot.isLive){
            try {
                hero.shotEnemyTank();//TODO 调用shotEnemyTank时才创建了shot对象，
                // 因此if判断语句里面要写hero.shot==null时也要执行shotEnemyTank方法
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }*/
            hero.shotEnemyTank();
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
            /*if (hero.shot != null && hero.shot.isLive) {//它本身就是Boolean值 直接这样写就可以 不用判断 是否为true
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
            }*/
            hitEnemyTank();
            hitHero();//是否击中我方坦克
            this.repaint();
        }
    }
}


