package com.cpystu.basic;

import java.util.Scanner;

public class HomeWork03 {
    //判断一个整数是否是水仙花数 153=1*1*1+3*3*3+5*5*5

    //思路：键盘输入一个整数num
    //取各个数位上的数字(判断num是几位数，再/对应的百分位 比如153/100的整数位就是1，153)
    //需要考虑怎么得到1 5 3


    //百分位=153/100=1
    //十分位=153%100（=53）/10=5
    //个位=153%10=3 定义三个变量存放三个数位
    public static void main(String[] args) {
        Scanner myScanner=new Scanner(System.in);//从键盘输入
        int n1;//百分位
        int n2;//十分位
        int n3;//gewei
        System.out.println("请输入一个数：");
        int a = myScanner.nextInt();
        if (a>=100&&a<=999){
            n1=a/100;
            n2=a%100/10;
            n3=a%10;
            if (n1*n1*n1+n2*n2*n2+n3*n3*n3==a){
                System.out.println(a+"是水仙花数");
            }else {
                System.out.println(a+"不是水仙花数");
            }
        }else {
            System.out.println("请输入一个三位数！！");
        }

    }
}
