package com.cpystu.mhl.view;

import com.cpystu.mhl.domain.DiningTable;
import com.cpystu.mhl.domain.Employee;
import com.cpystu.mhl.service.DiningTableService;
import com.cpystu.mhl.service.EmployeeService;
import com.cpystu.mhl.utils.Utility;

import java.util.List;

/**
 * @author 崔佩谣
 * @date 2025/4/15 12:58
 * @description: View层调用service
 */
public class MHLView {
    //TableStateView tableStateView=new TableStateView();
    private boolean Loop=true;
    //在View层调用Service对象，要先获得Service
    private EmployeeService employeeService=new EmployeeService();
    public static void main(String[] args) {
        new MHLView().mainMenu();
    }
    //编写方法循环展示一级菜单
    public void mainMenu(){

        while (Loop){
            System.out.println("========满汉楼========");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            int key = Utility.readInt();
            switch (key){//switch case break
                case 1:
                    //输入用户名和密码登录
                    System.out.print("请输入用户名：");
                    String id=Utility.readString(20);
                    System.out.print("请输入密码：");
                    String pwd=Utility.readString(20);

                    //到数据库判断
                    Employee employeeByIdAndPwd = employeeService.getEmployeeByIdAndPwd(id, pwd);
                    if (employeeByIdAndPwd!=null){//getEmployeeByIdAndPwd()返回一个Employee对象或者空
                        System.out.println("========您好 "+employeeByIdAndPwd.getName()+"登录成功~=======");
                        while (Loop){//展示二级菜单
                            System.out.println("========满汉楼二级菜单========");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 7 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            int choice=Utility.readInt();
                            switch (choice){
                                case 1:
                                    //定义一个TableStateView对象,调用他的方法
                                    new TableStateView().getListDiningTable();
                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                case 4:

                                    break;
                                case 5:

                                    break;
                                case 6:

                                    break;
                                case 7:
                                    Loop=false;
                                    break;
                                default:
                                    System.out.println("输入有误 请重新输入");
                            }
                        }
                    }else {
                        System.out.println("登录失败");
                    }
                    break;
                case 2:
                    Loop=false;
                    break;
                default:
                    System.out.println("输入有误，请重新选择：");
            }
        }
        System.out.println("您已成功退出满汉楼~");
    }

}
