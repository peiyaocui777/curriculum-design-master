package com.cpystu.basic;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Breaktest {
    //实现登录验证，有 3 次机会，如果用户名为"丁真" ,密码"666"提示登录成功，
//否则提示还有几次机会，请使用 for+break 完成
    //思路
    //用户需要从键盘输入用户名和密码，定义两个String变量分别为name passwd
    //需要提示还剩多少次机会，需要一个int变量记录 命名为chance
    public static void main(String[] args) {
        Scanner myScanner=new Scanner(System.in);//创建一个Scanner对象接收键盘输入信息
        String name="";//初始化
        String passwd="";
        int chance=3;//一共有三次机会
        for (int i=1;i<=3;i++){//一共三次机会 循环三次
            System.out.println("请输入用户名：");//提示用户输入信息
            name =myScanner.next();//得到键盘输入的用户名
            System.out.println("请输入密码：");
            passwd=myScanner.next();
            if ("丁真".equals(name)&&"666".equals(passwd)){//字符串的比较使用String类里面的equals，
                // 并且将目标字符串“丁真”与“666”放在前面防止出现空指针异常
                System.out.println("登录成功！");
                break;//符合条件就跳出循环
            }
            chance--;
            System.out.println("还有"+chance+"次机会");
        }
        System.out.println("超过三次无法输入！！");
    }
}
