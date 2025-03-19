package com.cpystu.basic;

import java.util.concurrent.ConcurrentHashMap;

public class HomeWork01 {
//某人有100000元，每次经过一次路口需要缴费
    //现金>50000时每次交%5，现金<=50000时每次交1000
    //计算可以经过几次路口（while+break）实现


    //思路：
    //定义一个变量cash存放现金，循环遍历现金的数目
    //每次循环判断cash>50000是否成立，若成立则缴费fee=cash*0.05
    //cash-=fee;若不成立则执行fee=1000
    //当cash<1000时break
    /*问题：
    已知有100000块钱，变量初始值就为100000；而且变量可以随时变化，不需要重新定义一个fee
    用到了分支判断if elseif else
    经过路口的次数用变量count表示，初始时count=0；每次判断完count++；
    while死循环的条件即while（true）我不明白
    *
    * */
    public static void main(String[] args) {
        double money=100000;
        int count=0;
        while(true){
           if (money>50000) {
               money = money - money * 0.05;
               count++;//count还在循环里面所以一直在累加
           } else if (money>1000) {
               money-=1000;
               count++;
           }else {
               break;
           }
        }
        System.out.println("10000块钱可以通过"+count+"次路口");
        System.out.println("还余"+money+"元");
        }
    }

