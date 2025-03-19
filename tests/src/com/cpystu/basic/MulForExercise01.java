package com.cpystu.basic;

import java.util.Scanner;

public class MulForExercise01 {
    //统计三个班的成绩情况，每个班5个同学。
    // 要求求出每个班的平均分和所有班的平均分 并统计三个班的及格人数。
    //分析：多层循环 外部循环遍历三个班，内部遍历每个同学
    //需要的变量有成绩score(double),班级总成绩cnum(double),总成绩anum(double)
    //统计三个班的及格人数:循环内加1个判断，score>=60时计数给变量passnum
    public static void main(String[] args) {
        Scanner myScanner=new Scanner(System.in);//定义一个自变量用来放键盘输入的成绩
double cnum=0;
double anum=0;
int passnum=0;
int a=1;
int c=1;
for (a=1;a<=3;a++){//变量a表示班级数
    for (c=1;c<=5;c++) {//变量表示学生数
        System.out.println("请输入第"+a+"个班级的第"+c+"名学生成绩：");//打印信息提示用户输入
        double score=myScanner.nextDouble();//用于接收用户输入的数据并把这个数据赋给score
        if (score>=60){//判断score是否及格
            passnum++;//及格就给passnum加1
        }
        cnum+=score;
    }
    System.out.println("第"+a+"个班级的平均分是："+cnum/5);
    anum+=cnum;
}
   System.out.println("三个班总分"+anum+"\n"+"总平均分"+anum/(3*15));
System.out.println("及格人数："+passnum);

    }
}
