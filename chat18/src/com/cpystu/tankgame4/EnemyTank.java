package src.com.cpystu.tankgame4;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {//敌人坦克.要使敌人坦克自由移动，将他做成线程
    //给敌人坦克一个shot属性，这个属性的类型是vector<>类型（原因：有多个shot且在线程中使用）
    Vector<Shot> shots = new Vector<>();//创建了shots集合，此时集合中还没有数据，即shots只是有了名字，还没被创建

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
        while (true) {
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
