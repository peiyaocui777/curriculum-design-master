package com.cpystu.smallchange;

import com.cpystu.smallchange.oop.SmallchangeSysOOP;

/*这个类用来调用SmallChangeSysOOP类的对象
* 显示主菜单*/
public class SmallChangeSysAPP {
    public static void main(String[] args) {
        //直接输入"main"与psvm是一样的效果
        new SmallchangeSysOOP().mainMenu();
        System.out.println("我要提交了 ");
    }
}
