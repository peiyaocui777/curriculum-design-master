package com.cpystu.basic;

public class HomeWork04 {
    //输出a-z Z-A

    //分析：单个的char类型
    //本质上是整数 可以加减运算 ‘b’='a'+1 c='a'+2
    //该题可以用循环完成
    public static void main(String[] args) {
        char c1='a';
        char c2='Z';
        for (c1='a';c1<='z';c1++){//循环输出a-z的值
            System.out.print(c1+"\t");
        }
        for (c2='Z';c2>='A';c2--){
            System.out.print(c2+"\t");
        }
    }
}
