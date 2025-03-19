package com.cpystu.basic;

import com.cpystu.basic.AA;

public class HomeWork06 {//主类
    //编写一个AA类，他有一个方法是判断一个数是奇数还是偶数，返回值为boolean类型
    //数据类型：boolean
    //形参：num
    //变量名ifood
    //成员方法的定义：
    //访问修饰符 返回数据类型 变量名（）（形参）{方法体} return（非必要，如果没有返回值时可不要return）
    public static void main(String[] args) {//程序入口
     AA a=new AA();
        //boolean isodd = a.isodd(3);
        //System.out.println("isodd");
        if (a.isodd(1)){
            System.out.println("是ji数");
        }else {
            System.out.println("是偶数");
        }
    }

    }






