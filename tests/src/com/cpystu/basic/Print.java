package com.cpystu.basic;

public class Print {
    //根据行。列。字符打印对应行数 列数 的字符
    //如4行4列#
    //####
    //####
    //####
    //####

    //访问修饰符 返回数据类型 方法名（）参数 {方法体} return返回值
    //public   void  print()  int n1 int n2 char c return 可有可无
    public static void main(String[] args) {
        Print p=new Print();
        p.print(4,4,'&');
    }
    public void print(int n1,int n2,char c){
        for (int i=1;i<=n1;i++){
            for (int j=1;j<=n2;j++){
                System.out.print(c);
            }
            System.out.println();
        }

    }
}
class bb{

}
