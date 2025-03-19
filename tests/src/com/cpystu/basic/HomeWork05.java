package com.cpystu.basic;

public class HomeWork05 {
    //求1+（1+2）+（1+2+3）+。。。+（1+2+3+...+100）
    //分析：再另外定义一个变量来存放循环后累加的值，第一次循环时num=1
    //anum=1;第二次循环num+num+i,anum=num+anum
    public static void main(String[] args) {
        int num=0;//定义一个变量存放循环累加的值并初始化
        int anum =0;//所有的和
        for (int i=1;i<=100;i++){
            num+=i;
            anum+=num;
        }
        System.out.println("累加的结果是"+anum);
    }
}
