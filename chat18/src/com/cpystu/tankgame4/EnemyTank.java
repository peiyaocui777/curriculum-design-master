package src.com.cpystu.tankgame4;

import lombok.Setter;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {//敌人坦克.要使敌人坦克自由移动，将他做成线程
    //给敌人坦克一个shot属性，这个属性的类型是vector<>类型（原因：有多个shot且在线程中使用）
    Vector<Shot> shots = new Vector<>();//创建了shots集合，此时集合中还没有数据，即shots只是有了名字，还没被创建
    //增加一个属性，使得enemyTank可以得到EnemyTanks集合的对象
    @Setter
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //通过setEnemyTank的方法将Vector集合传过来 TODO  ？？

    /*public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks=enemyTanks;
    }*/
    //编写一个方法，判断enemyTank有没有与Vector集合里面的其他坦克碰撞，方法的返回类型为Boolean
    public boolean isTouchEnemyTank() {
        //switch case break 判断当前enemyTank的方向
        switch (this.getDirect()) {
            //遍历取出vector中的所有坦克
            case 0://当前坦克方向向上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);//TODO 循环遍历为什么要放在switch判断语句里面
                    //当前enemyTank也在集合中，不与自己比较是否碰撞
                    if (enemyTank != this) {
                        //集合中取出的坦克有上下 左右移动的两个形态，分为两种情况
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {//上下移动
                            //根据this坦克的左右两个坐标是否进入到vector集合中遍历得到的坦克的区域
                            // （这个区域由他的坐标为原点确定）内判断两个坦克是否重合
                            //1.取出的enemyTank上下移动时x坐标的变化范围为（enemyTank.getX()-enemyTank.getX()+40)
                            //y坐标的变化范围为（enemyTank.getY()-enemyTank.getY()+60）
                            //2.this坦克的左上角坐标为（getX(),getY()）,右上角坐标为（getX()+40,getY()）
                            //3.判断方法：this的左右两个坐标在enemyTank的x、y坐标范围内，说明两个坦克重合了
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60
                                    && this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40) {//todo 写一起不行吗
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {//左右移动
                            //1.取出的enemyTank上下移动时x坐标的变化范围为（enemyTank.getX()-enemyTank.getX()+60)
                            //y坐标的变化范围为（enemyTank.getY()-enemyTank.getY()+40）
                            //2.this坦克的左上角坐标为（getX(),getY()）,右上角坐标为（getX()+40,getY()）

                            //this坦克左上角与enemyTank x\y坐标范围
                            if (this.getX()>= enemyTank.getX()
                                    &&this.getX()<= enemyTank.getX()+60
                                    &&this.getY()>= enemyTank.getY()
                                    &&this.getY()<=enemyTank.getY()+40){
                                return true;
                            }
                            //this坦克右上角与enemyTank x\y坐标范围
                            if (this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<= enemyTank.getX()+60&&
                                    this.getY()>= enemyTank.getY()+40&&
                                    this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                        }


                    }
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;

        }
    }

    //当Enemy对象被绘制时shot有坐标值
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {

        return super.toString();
    }

    @Override
    public void run() {//重写Runnable接口中的run方法，让坦克移动
        while (true) {//创建子弹放在while循环里面
            //TODO 判断子弹的数量 以及enemyTank的存活状态，决定要不要创建新的子弹？？为什么要写在这个位置
            if (isLive && shots.size() < 1) {//如果当前enemyTank没有被销毁，并且他的子弹已经消失，就再创建一颗子弹
                Shot s = null;
                //由于enemyTank不停的移动，需要使用switch case break语句判断当前enemyTank的方向，再根据不同的方向创建子弹
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, getDirect());
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, getDirect());
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, getDirect());
                        break;
                }
                //将上面创建的子弹加入到shots集合里面
                shots.add(s);
                //启动子弹线程
                new Thread(s).start();
            }
            //坦克有0 1 2 3四个方向，根据不同的朝向调用不同的Tank类中的move方法
            switch (getDirect()) {//在方向上移动
                case 0://朝上
                    for (int i = 0; i < (int) (Math.random() * 400); i++) {
                        //用if判断限制坦克的移动范围
                        if (getY()>0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < (int) (Math.random() * 400); i++) {
                        if (getX()+60<1000){
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 2:
                    for (int i = 0; i < (int) (Math.random() * 400); i++) {
                        if (getY()+60<750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < (int) (Math.random() * 400); i++) {
                        if (getX()>0){
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //随机改变坦克的方向
            //1.父类的setDirect方法
            //2.TODO Math.random()得到的是【0-1）随机小数
            //3.(int)(Math.random()*4) 得到【0-3）之间的整数，即0 1 2 3
            setDirect((int) (Math.random() * 4));
            //TODO 写多线程并发程序要明白该线程什么时候退出 结束
            if (!isLive) {
                break;
            }
        }
    }
}
