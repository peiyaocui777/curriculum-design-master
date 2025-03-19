package com.cpystu.basic;

import java.util.Scanner;

public class HomeWork02 {
    //实现判断一个整数，属于那个范围，大于0 小于0 等于0
    //使用分支判断 if else-if else

    //分析：准备实现从键盘输入，提高灵活性
    public static void main(String[] args) {
        Scanner myScanner=new Scanner(System.in);
        System.out.println("请输入一个整数：");//提示用户输入
        int intel=myScanner.nextInt();//接收数值存放在Intel中
        if (intel>0){
            System.out.println(intel+">0");
        } else if (intel==0) {
            System.out.println(intel+"=0");
        }else {
            System.out.println(intel+"<0");
        }
    }
}
