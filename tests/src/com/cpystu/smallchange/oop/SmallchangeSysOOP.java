package com.cpystu.smallchange.oop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*该类是完成零钱通的各个功能的类
 * 使用OOP编程
 * 把各个功能写成一个方法
 * */
public class SmallchangeSysOOP {
    //类的属性
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
    double money = 0;//定义并初始化
    double balance = 0;
    //Date date=new Date();?
    Date date = null;//date是java.util.Date,即java工具里面的日期类
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//?用于日期格式化的类
    //()里面是参数?表示想要的格式 (这是在创建对象)
    //String key="";

    //4.消费
    String note = "";//新增一个变量表示消费说明,同样需要用户输入

    //方法在类里面写
    //1.完成零钱通界面
    public void mainMenu() {
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
                    this.detail();
                    break;//!
                case 2:
                    this.incom();
                    break;//!
                case 3:
                    this.pay();
                    break;//
                case 4:
                    this.exit();
                    break;//?break为什么放在这里?如果输入的是n或者其他的会怎么用

                default:
                    System.out.println("选择有误！！请输入1-4之间的数字");
            }
        } while (loop);
    }

    //2.零钱明细
    public void detail() {
        System.out.println(details + "\n");
    }

    //收益入账
    public void incom() {
        //System.out.println("收益金额:"+money);× money是从键盘得到的金额,目前是0
        System.out.print("收益金额:");//去掉ln不换行,使这句话与下面的在同一行
        money = myScanner.nextDouble();//将从键盘获取到的double类型的值赋给money
        //判断输入的金额是否正确,排除错误的(输入的金额不正确时直接break)
        if (money <= 0) {
            System.out.println("收益金额应大于0");
            return;//!!方法里使用return表示返回
        }
        //!!编程思想:排除错误即只要不符合条件就break,直到符合要求再执行下面的代码
        balance += money;//余额累加
        //拼接收益入账信息并赋给details,此时case 1里面得到的就是拼接后的值,实现了明细的动态增长
        date = new Date();//获取当前日期
        //得到目标格式日期的方法:sdf.format(date);?(可能需要解释)
        details += "\n入账金额\t+" + money + "\t" + sdf.format(date) + "\t余额:" + balance;
        //
        return;//!
    }

    //支出
    public void pay() {
        System.out.print("消费金额:");
        money = myScanner.nextDouble();
        //校验
        if (money <= 0 || money > balance) {//消费金额要大于0
            System.out.println("消费金额应在0-" + balance + "之间");
            return;//void,没有返回类型所以return后不用跟其他内容
        }
        System.out.print("消费说明:");//不换行
        note = myScanner.next();
        balance -= money;
        date = new Date();//!!!重新获取当前日期
        details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t余额:" + balance;
        return;//退出方法不再执行后面的代码
    }

    //退出
    public void exit() {
        String choice = "";//
        while (true) {//死循环实现"必须输入正确的y/n,否则循环
            System.out.println("你确定要退出吗?y/n");
            choice = myScanner.next();//choice需要定义在while循环外面,否则其他地方不能使用
            if ("y".equals(choice) || "n".equals(choice)) {//字符串比较用equals
                break;
            } else {
                System.out.println("请输入y/n");
                //break;加了break就退出while循环了,与我们的要求不符
            }
        }//死循环结束后再来判断输入的是y还是n
        if (choice.equals("y")) {
            loop = false;
        }
    }


}
//测试sdfg
