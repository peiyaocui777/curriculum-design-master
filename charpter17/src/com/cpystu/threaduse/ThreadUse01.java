package com.cpystu.threaduse;

/**
 * 线使用01
 *
 * @author cuipeiyao
 * @date 2025/03/17
 */
public class ThreadUse01 {
    public static void main(String[] args) {
        //创建一个cat对象，可以啊当作线程使用
        Cat cat = new Cat();
        cat.start();//启动线程,执行cat的run()
        //start()调用start0（），start0（）是本地方法，由JVM机调用，
        // 底层是c/c++实现的
        // 真正实现多线程的是start0而不是run()
        //不使用cat.run();语句是因为run()就是一个普通的方法，没有
        //真正启动一个线程，程序会把run()执行完毕再往下走，造成阻塞
    }
    //一个类继承Thread类以后就可以当作线程使用
    static class  Cat extends Thread{
        public Cat() {
        }

        int times=0;//设置变量统计次数
        /*public void run(){
            System.out.println("喵喵，我是一只小猫咪~");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        //run方法在 thread类实现了Runnable接口的run方法
        @Override
        public void run() {
            while (true){
            //重写run方法，写自己的业务逻辑
            System.out.println("喵喵，我是一只小猫咪~"+(++times));
            try {
                Thread.sleep(1000);//CTRL+ALT+T快捷键
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (times==8){
                break;
            }
        }
    }
    }
}
