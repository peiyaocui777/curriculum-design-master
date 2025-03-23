package com.cpystu.threaduse.homework;
/*
 * 在main方法中启动两个线程
 * 第一个线程A循环打印100以内整数
 * 直到第二个线程B从键盘读取了“Q”命令*/

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

//由线程B控制线程A退出：通知方式（本质就是在B线程修改A线程的一个变量
// 右真变为假，此时A线程就退出了）
//要实现通知方式，需要使B持有A的对象，有以下几种方法
//1.将A类当作B的属性（私有），（1）通过get set方法（2）构造方法
//2.直接在B类创建一个A类对象
public class HomeWork01 {
    public static void main(String[] args) {
        //TODO 第一步 创建A对象
        A a = new A();
        a.start();
        //TODO 传入A对象
        B b = new B(a);//构造方法
//       b.setA(a);//set方法
        b.start();
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor//wucan
//@Slf4j
class A extends Thread {
    private boolean Loop = true;//Loop变量控制循环
private static final Logger logger = LoggerFactory.getLogger(A.class);


    @Override
    public void run() {
        while (Loop) {
            //Math.random取得0-1之间的随机数，双精度类型
            System.out.println((int) (Math.random() * 100 + 1));
            logger.debug("sss {},A {}",Loop,A.class);
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
class B extends Thread {
    public A getA() {
        return a;
    }

    public void setA(A a) { //TODO 设置值时候才调用
        this.a = a;
    }

    //持有A的对象
    private A a;

    public B(A a) {
        //TODO 构造方法 创建对象  A为属性 传入A
        this.a = a;//TODO this 就是B.set A 类似set方法 一个创建对象的时候就要传入
    }

    private Scanner MyScanner = new Scanner(System.in);//??需要从前往后写，不能先new 再.var


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
