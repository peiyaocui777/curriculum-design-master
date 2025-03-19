package com.cpystu.basic;

public class Test99 {
    public static void main(String[] args) {
        //打印99乘法表 外层从1-9 内层每次循环都-1
        int s=1;
        int i=1;

        for(s=1;s<=9;s++){
            for (i=1;i<=s;i++) {
                int answer=0;
                answer=s*i;
                System.out.println(s+"×"+i+"="+answer);

            }
            System.out.println();
        }
        TestBookPrice testBookPrice = new TestBookPrice();
//        testBookPrice.info();
    }
}
