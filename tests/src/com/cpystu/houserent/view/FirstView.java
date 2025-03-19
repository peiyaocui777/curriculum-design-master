package com.cpystu.houserent.view;

import com.cpystu.houserent.domain.Regist;
import com.cpystu.houserent.service.LoginService;
import com.cpystu.houserent.service.RegistService;
import com.cpystu.houserent.utils.Utility;

public class FirstView {
    boolean Loop=true;
    public void regist(){
        //注册方法
        RegistService registService = new RegistService();
        registService.regist();
    }
    public void login(Regist regist){
        //登录
        LoginService loginService = new LoginService();
        Utility.loginuser(regist);
    }
    //退出
    public void exit() {
        char c = Utility.readConfirmSelection();//Utility方法中有这个循环确认功能，直接调用
        if (c == 'Y') {//判断c的值是Y还是N
            Loop = false;
        }
    }
    //打印出一个页面，给用户提供注册 登录选项
    public boolean choice(Regist regist){

       // Scanner myScanner = new Scanner(System.in);
        do {
            System.out.println("======欢迎来到房屋租赁系统======");
            System.out.println("1 注册");
            System.out.println("2 登录");
            System.out.println("3 退出");
            System.out.print("请选择：");
            //int key = myScanner.nextInt();可以直接使用工具类

            int key = Utility.readInt();
            switch (key){
                case 1:
                    return new RegistService().regist();
                case 2:
                    return new Utility().loginuser(regist);
                case 3:
                    exit();
                    return Loop;
                default:
                    System.out.println("请重新选择");
                    return true;
            }
        }while (Loop);

    }
}
