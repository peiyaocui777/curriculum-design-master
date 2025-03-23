package com.cpystu.threaduse;

/**
 * 用Runnable实现两个子线程
 *
 * @author cuipeiyao
 * @date 2025/03/18
 */
//线程每隔1秒输出“Hello World”，10次后退出
//T2每隔1秒输出“hi”，5次后退出
public class TreadMore {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        //t1 t2里没有start（）方法，无法启动线程，使用Thread作为代理实现这个功能
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();//启动线程T1
        thread2.start();//启动线程T2
    }
}

class T1 implements Runnable {
    int count = 0;//定义一个变量记录输出次数

    @Override
    public void run() {
        while (true) {
            System.out.println("Hello,World!" + (++count));
            try {
                Thread.sleep(1000);//休眠1秒
                //？T1类里面
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count==10){
                break;
            }
        }
    }
}
class T2 implements Runnable {
    int count = 0;//定义一个变量记录输出次数

    @Override
    public void run() {
        while (true) {
            System.out.println("Hi!" + (++count));
            try {
                Thread.sleep(1000);//休眠1秒
                //？T1类里面
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count == 5) {
                break;
            }
        }
    }
}
