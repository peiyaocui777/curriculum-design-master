package com.cpystu.basic;

import java.util.Scanner;

public class Fortest {
    public static void main(String[] args) {
      ////打印 1~100 之间所有是 9 的倍数的整数，
        // 统计个数 及 总和.[化繁为简,先死后活]
        //考虑到代码的实用性，在这里加上Scanner键盘输入 试一下效果
        Scanner myScanner=new Scanner(System.in);
        System.out.print("请输入起始值：");
        int start=myScanner.nextInt();
        System.out.print("请输入结束值：");
        int end=myScanner.nextInt();
        System.out.print("请输入倍数：");
        int n=myScanner.nextInt();
        int num=0;//计算总和
        int count=0;//用来统计9的倍数的个数
        int i;
        for (i=start;i<=end;i++){
            if (i%n==0){
                System.out.println("i="+i);
                num+=i;
                count++;
            }
        }
         System.out.println("有"+count+"个数是"+n+"的倍数");
        System.out.println("总和为"+num);
    }
}
