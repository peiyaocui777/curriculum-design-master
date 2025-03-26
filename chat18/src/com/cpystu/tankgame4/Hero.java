package src.com.cpystu.tankgame4;

import java.util.Vector;

/**
 * 英雄
 *
 * @author cuipeiyao
 * @date 2025/03/16
 */
public class Hero extends Tank {
    //Hero要操作shot类 得先有shot类的对象
    Shot shot = null;//先声明shot对象，内存里面没有分配数据
    //要使我方坦克能发射多颗子弹，创建一个vector集合存放所有子弹
    Vector<Shot> shots = new Vector<>();
    //Shot shot1=null;//先声明shot对象，内存里面没有分配数据
    //声明就相当于给一个还没出生的小孩取了个名字？？
    public Hero(int x, int y) {
        super(x, y);
    }//这不是构造方法吗 是呀 里面没有初始化是不 是的 欧克

    //Hero有一个发射子弹的方法shotEnemyTank().
    public void shotEnemyTank() throws InterruptedException {//或者传递参数进来 要么在这里设置shot
        shot = new Shot(getX(), getY(), getDirect());//这里是初始化 我给他注释了 //TODo 有什么不一样 两行之间 x 这行怎么写的
        //shot1 = new Shot(getX(),getY(),getDirect());//这里是初始化 我给他注释了 //TODo 有什么不一样 两行之间 x 这行怎么写的
        // 要么在 mypanel 类里面set 属性 要么在这里 刚刚没有关联起来 因为不是在这个类初始化的
         //加一个判断，使得vector集合里同时只能存在5颗子弹
        if (shots.size()==5){
            return;//一旦有5颗子弹就返回 方法不再往下执行，无法创建新的子弹
        }
        // 1.新建一个shot对象（此时由栈指向堆，即分配了数据），通过get方法拿到他的属性
        // 2.但是这个地方有问题：short对象创建出来的位置要根据Hero的位置的方向确定，不能直接创建，
        // 要使用switch判断一下，根据Hero不同的朝向和坐标分别创建不同状态的子弹
        switch (getDirect()) {//得到Hero对象的direct属性，并判断朝向
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);//试一下能不能使用Direct  new 是什么意思 创建 创建了 然后呢 你要怎么用 是否使用了呢
                //shot1=new Shot(getX()+20,getY(),0);//试一下能不能使用Direct  new 是什么意思 创建 创建了 然后呢 你要怎么用 是否使用了呢
                // 下面的没有名字吗 你没有引用 上

                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                //shot1=new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                //shot1=new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                //shot1=new Shot(getX(),getY()+20,3);
                break;
        } //todo 等于直接跳过了 都是创建的新对象 那咋办
        //System.out.println("shot1子弹坐标x="+ shot1.x +"y="+shot1.y);//我猜改空指针了
        //System.out.println("子弹坐标x=" + shot.x + "y=" + shot.y);//我猜改空指针了
//        System.out.println("shot1子弹坐标====x="+ getX() +"y="+getY());
//        System.out.println("子弹坐标====x="+ getX() +"y="+getY());
        //todo 将新创建的shot加入到shots
        shots.add(shot);
        //启动shot线程
        //这里没有编写有关线程的代码，但是我们的Hero里创建的shot对象实现了Runnable方法，就可以通过shot启动线程
        //就类似于在主方法里面创建一个对象启动线程
        Thread thread = new Thread(shot);//创建一个Thread对象，把shot对象传进去（为了调用其中的run()）
        //Thread.sleep(500);
        //Thread thread1 = new Thread(shot1);//创建一个Thread对象，把shot对象传进去（为了调用其中的run()）


        thread.start();//由thread对象调用Thread类里的start()启动线程
        //thread1.start();//由thread对象调用Thread类里的start()启动线程
    }
}
