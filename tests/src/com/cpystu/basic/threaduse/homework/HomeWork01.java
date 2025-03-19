package com.cpystu.basic.threaduse.homework;
/*
 * 在main方法中启动两个线程
 * 第一个线程A循环打印100以内整数
 * 直到第二个线程B从键盘读取了“Q”命令*/

import com.cpystu.basic.Breaktest;

import java.util.Scanner;

//由线程B控制线程A退出：通知方式（本质就是在B线程修改A线程的一个变量
// 右真变为假，此时A线程就退出了）
//要实现通知方式，需要使B持有A的对象，有以下几种方法
//1.将A类当作B的属性（私有），（1）通过get set方法（2）构造方法
//2.直接在B类创建一个A类对象
public class HomeWork01 {
    public static void main(String[] args) {
        A a = new A();
        a.start();
        B b = new B(a);//构造方法
       //q b.setA(a);//set方法
        b.start();
    }
}

class A extends Thread {
    private boolean Loop = true;//Loop变量控制循环

    @Override
    public void run() {
        while (Loop) {
            //Math.random取得0-1之间的随机数，双精度类型
            System.out.println((int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("A线程退出");
    }
//通过setLoop方法修改Loop变量
    public void setLoop(boolean loop) {
        Loop = loop;
    }
}

class B extends Thread {
    //持有A的对象
    private A a;

    public B(A a) {
        this.a = a;
    }

    private Scanner MyScanner = new Scanner(System.in);//??需要从前往后写，不能先new 再.var

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
    //重写run方法

    @Override
    public void run() {
        while (true) {
            //接收用户输入
            System.out.println("请输入指令（Q表示退出）");
            char key = MyScanner.next().toUpperCase().charAt(0);//接收输入的第一个字符并转换成大写
            if (key == 'Q') {
                //以通知的方式结束A线程
                a.setLoop(false);//调用A类的对象的setLoop方法修改Loop为false
                System.out.println("B线程退出。。");
                break;
            }
        }
    }
}
