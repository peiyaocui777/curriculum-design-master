package com.cpystu.mhl.view;

import com.cpystu.mhl.utils.Utility;

/**
 * @author 崔佩谣
 * @date 2025/4/16 15:57
 * @description: 显示预定餐桌的界面，包含预定功能
 */
public class OrderTableView {
    boolean Loop=true;
    //编写方法OrderTable（），返回一个布尔值
    public boolean orderTable() {
        //先把界面打印出来
        while (Loop) {
            System.out.println("========预定餐桌========");
            System.out.print("请输入要预定的餐桌编号（-1退出）：");
            int key = Utility.readInt();
            if () {
                //这里要进行校验，如输入的餐桌号是否存在，是否空闲等
                System.out.print("是否确认预定（Y/N）:");
                char choice = Utility.readChar();
                if () {//输入Y y,确认
                    System.out.print("预定人姓名：");
                    String name = Utility.readString(50);
                    System.out.print("预定人电话：");
                    String tel = Utility.readString(50);
                    //写入数据库
                } else {//输入N,返回到"请输入要预定的餐桌编号"
                        //也可以不写这个判断吧，只要输入的不是Y y或者-1，就一直在循环
                }
            } else if (key == -1) {
                Loop = false;
                break;
            }else {
                System.out.println("输入有误，请重新输入！");
            }

            return false;
        }
    }


}
