package com.cpystu.smallchange;

import javax.naming.ldap.SortResponseControl;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//        ctrl + alt +l 统一格式的快捷键
public class SmallChangeSys {
    //化繁为简
    //1.先实现显示菜单，并可以选择菜单给出对应提示
    //2.使用字符串拼接实现零钱通明细(先把固定的打印出来再考虑其他的内容)
    //3.完成收益入账(根据需求增加新的变量)
    //4.消费
    //5.退出 询问是否退出

    //优化:
    //6.用户输入4退出时提示:你确定要退出吗?y/n",
    // 必须输入正确的y/n,否则循环输入指令,直到输入y或n
    //7.判断输入的金额是否正确(排除错误法的校验方式)
    public static void main(String[] args) {
        //使用do-while循环控制整个系统，退出条件是 用户选择了4
        //因此需要定义一个变量loop并初始化为true，用户输入其他
        //值系统一直循环，只有输入4时loop=false,退出循环
        boolean loop = true;
        //因为需要用户输入，所以需要Scanner
        Scanner myScanner = new Scanner(System.in);//接受输入
        int key;//？？？不能int key;
        //2.完成零钱通明细
        //首先打印一条表头,余下的信息是根据入账和支出决定的,用变量details接收其他信息并回填到case 1里面
        String details = "---------零钱通明细---------";//后面怎么通过details实现动态增长?
        //! 使用details+=实现拼接
        //3.收益入账
        //根据明细里的内容需要增加几个变量,分别是money每次输入(收益与支出)的金额
        //balance账户余额 日期date
        double money=0;//定义并初始化
        double balance=0;
        //Date date=new Date();?
        Date date=null;//date是java.util.Date,即java工具里面的日期类
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//?用于日期格式化的类
        //()里面是参数?表示想要的格式 (这是在创建对象)
        //String key="";

        //4.消费
        String note="";//新增一个变量表示消费说明,同样需要用户输入

        do {
            System.out.println("==========零钱通菜单========");
            System.out.println("\t\t1 零钱通明细");
            System.out.println("\t\t2 收益入账");//手动输入收益金额
            System.out.println("\t\t3 消费");
            System.out.println("\t\t4 退   出");
            System.out.print("请选择（1-4）：");
            key = myScanner.nextInt();//!!!!调用nextInt（）方法接收数字并返回给key
            //使用switch分支判断用户输入的数字，输出对应的信息
            switch (key) {
                case 1:
                    System.out.println(details + "\n");//\n加在后面就在这一行的下面换行
                    //System.out.println("\n"+details);//\n加在前面就在这一行的上面换行
//                    loop=false;
                    break;//!
                case 2:
                    //System.out.println("收益金额:"+money);× money是从键盘得到的金额,目前是0
                    System.out.print("收益金额:");//去掉ln不换行,使这句话与下面的在同一行
                    money=myScanner.nextDouble();//将从键盘获取到的double类型的值赋给money
                    //判断输入的金额是否正确,排除错误的(输入的金额不正确时直接break)
                    if (money<=0){
                        System.out.println("收益金额应大于0");
                        break;
                    }
                    //!!编程思想:排除错误即只要不符合条件就break,直到符合要求再执行下面的代码
                    balance+=money;//余额累加
                    //拼接收益入账信息并赋给details,此时case 1里面得到的就是拼接后的值,实现了明细的动态增长
                    date=new Date();//获取当前日期
                    //得到目标格式日期的方法:sdf.format(date);?(可能需要解释)
                    details+="\n入账金额\t+"+money+"\t"+sdf.format(date)+"\t余额:"+balance;
                    //
                    break;//!
                case 3:
                    System.out.print("消费金额:");
                    money=myScanner.nextDouble();
                    //校验
                    if (money<=0||money>balance){//消费金额要大于0
                        System.out.println("消费金额应在0-"+balance+"之间");
                        break;
                    }
                    System.out.print("消费说明:");//不换行
                    note=myScanner.next();
                    balance-=money;
                    date=new Date();//!!!重新获取当前日期
                    details+="\n"+note+"\t-"+money+"\t"+sdf.format(date)+"\t余额:"+balance;
                    break;//
                case 4:
//                    System.out.println("4 退出");
////                    loop = false;
////                    //loop=false后程序不是退出了吗为什么还需要break
////                    break;//?~！如果不加break的话，程序会继续执行default
////                //输出 选择有误！！请输入1-4之间的数字
////                //loop=false;//输入4时执行这行代码，退出循环

                    /*
                   代码改进:
                   1.用户输入4退出时提示:你确定要退出吗?y/n",
                   必须输入正确的y/n,否则循环输入指令,直到输入y或n

                   (1)定义一个变量接收用户输入的字符
                   (2)判断这个字符是不是y/n
                   (3)如果是y就执行loop=false执行退出操作,否则继续循环
                   (4)使用while break 死循环实现"必须输入正确的y/n,否则循环输入指令,直到输入y或n"
                   */
                    String choice="";//
                    while (true) {//死循环实现"必须输入正确的y/n,否则循环
                        System.out.println("你确定要退出吗?y/n");
                        choice = myScanner.next();//choice需要定义在while循环外面,否则其他地方不能使用
                        if ("y".equals(choice) || "n".equals(choice)) {//字符串比较用equals
                            break;
                        }else {
                            System.out.println("请输入y/n");
                            //break;加了break就退出while循环了,与我们的要求不符
                        }
                    }//死循环结束后再来判断输入的是y还是n
                    if (choice.equals("y")){
                        loop=false;
                    }
                    break;//?break为什么放在这里?如果输入的是n或者其他的会怎么用

                default:
                    System.out.println("选择有误！！请输入1-4之间的数字");
            }
        } while (loop);
        System.out.println("------退出了项目------");
    }
}
