package com.cpystu.mhl.view;

import com.cpystu.mhl.domain.DiningTable;
import com.cpystu.mhl.service.DiningTableService;

import java.util.List;

/**
 * @author 崔佩谣
 * @date 2025/4/16 15:47
 * @description: 显示餐桌状态的界面
 */
public class TableStateView {
    //new一个DiningTableService属性，用来使用他的list方法
    private DiningTableService diningTableService=new DiningTableService();
    //在这里写一个方法，显示这一级菜单并且得到查询到的DiningTable表
    public void getListDiningTable(){
        System.out.println("========显示餐桌状态========");
        List<DiningTable> list = diningTableService.listDiningTable();
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("========显示完毕========");
    }
}
