package com.cpystu.mhl.view;

import com.cpystu.mhl.service.BillService;
import com.cpystu.mhl.service.DiningTableService;
import com.cpystu.mhl.service.MenuService;
import com.cpystu.mhl.utils.Utility;

import java.time.Year;

/**
 * @author 崔佩谣
 * @date 2025/4/22 15:15
 * @description: 点餐的界面
 */
public class BillView {
    //定义BillService属性
    private BillService billService=new BillService();
    private DiningTableService diningTableService=new DiningTableService();
    private MenuService menuService=new MenuService();
    boolean Loop =true;
    public void orderView() {
        while (Loop) {
            System.out.println("========点餐服务========");
            System.out.print("请选择点餐的桌号（-1退出）：");
            int orderDiningTableId = Utility.readInt();
            if (orderDiningTableId == -1) {
                System.out.println("========取消点餐========");
                return;
            }
            if (diningTableService.getDiningTableById(orderDiningTableId) == null) {
                System.out.println("该桌号不存在，请重新选择");
                Loop=false;
            }
            //加个循环，如果菜品不存在就退出到这一层的循环
            while (Loop) {
                System.out.print("请选择菜品编号（-1退出）：");
                int menuId = Utility.readInt();
                if (menuId == -1) {
                    System.out.println("========取消点餐========");
                    return;
                }
                if (menuService.getMenuById(menuId) == null) {
                    System.out.println("该菜品不存在，请重新选择");
                    Loop = false;
                }

            System.out.print("请输入菜品数量（-1退出）：");
            int nums = Utility.readInt();
            if (nums == -1) {
                System.out.println("========取消点餐========");
                return;
            }
            System.out.print("是否确认点这个菜（Y/N）:");
            char choice = Utility.readConfirmSelection();
            if ('N'==choice){
                System.out.println("========取消点餐========");
                return;
            }else {
                //确认点餐，生成账单并更新状态
                billService.orderMenu(menuId,nums,orderDiningTableId);
                diningTableService.updateDiningTableState(orderDiningTableId,"就餐中");
                return;
            }
        }
        }
    }
}
