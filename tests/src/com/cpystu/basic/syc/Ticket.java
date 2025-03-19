package com.cpystu.basic.syc;

/**
 * @author cuipeiyao
 * @date 2025/03/18
 */
//分别用继承Tread和实现Implements的方式实现
//三个窗口同时售卖100张票，
//体会这两种方式的区别，观察会出现什么问题
//区别：继承Thread的方法需要创建3个对象，接口的方式是对同一个对象的操作
//问题：超卖现象，这是因为同一时间有三个线程同时操作

//在这里我们用sycrolized同步解决上面的问题
public class Ticket {
    public static void main(String[] args) {
        //1.继承Thread的方式
//        SellTickets01 sellTickets01 = new SellTickets01();//创建第一个对象，相当于一个窗口
//        SellTickets01 sellTickets011 = new SellTickets01();//创建第二个对象，相当于一个窗口
//        SellTickets01 sellTickets012 = new SellTickets01();//创建第三个对象，相当于一个窗口
//        sellTickets01.start();
//        sellTickets011.start();
//        sellTickets012.start();
        //2.实现接口的方式
//        SellTicket02 sellTicket02 = new SellTicket02();//只创建了一个对象
//
//        Thread thread01 = new Thread(sellTicket02);//第一个线程窗口
//        thread01.start();//线程启动
//        Thread thread02 = new Thread(sellTicket02);
//        thread02.start();
//        Thread thread03 = new Thread(sellTicket02);
//        thread03.start();

        //使用synchronized的效果
        SellTicket03 sellTicket03 = new SellTicket03();
        Thread thread1 = new Thread(sellTicket03);
        thread1.start();
        Thread thread2 = new Thread(sellTicket03);
        thread2.start();
        Thread thread3 = new Thread(sellTicket03);
        thread3.start();
    }
}

//使用线程同步解决超售问题
class SellTicket03 implements Runnable {
    //写一个Sell方法，给它一个syc锁，使得同一时刻访问数据的只有一个线程
    private boolean Loop = true;//Loop变量控制while循环

    private int ticketsNum = 100;//这个方式只创建了一个对象，因此ticketsNums不需要为静态

    public synchronized void Sell() {//同步机制，同一时刻只允许一个线程进入方法
        if (ticketsNum <= 0) {
            System.out.println("已售空");
            Loop=false;
            return;
        }
        //休眠
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //有票的情况下
        System.out.println("窗口" + Thread.currentThread().getName() + "售出1张票 剩余" + (--ticketsNum) + "张");
    }

    @Override
    public void run() {

        while (Loop) {
            Sell();
        }
    }
}

//继承的方式
class SellTickets01 extends Thread {
    //100张票应该写成静态属性，以供三个对象（窗口）使用
    private static int ticketsNum = 10;

    @Override
    public void run() {//重写父类的方法
        //1.先判断余票数量，小于等于0就退出不能继续售卖
        while (true) {

        }

    }
}

//实现接口的方式
class SellTicket02 implements Runnable {
    private int ticketsNum = 10;//这个方式只创建了一个对象，因此ticketsNums不需要为静态

    @Override
    public void run() {
        while (true) {
            if (ticketsNum <= 0) {
                System.out.println("已售空");
                break;
            }
            //休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //有票的情况下
            System.out.println("窗口" + Thread.currentThread().getName() + "售出1张票 剩余" + (--ticketsNum) + "张");
        }

    }
}

