package com.cpystu.mhl.service;

import com.cpystu.mhl.dao.BillDAO;

import java.beans.beancontext.BeanContext;
import java.util.UUID;

/**
 * @author 崔佩谣
 * @date 2025/4/21 17:27
 * @description: 编写业务逻辑，通过调用BillDao实现
 */
public class BillService {
    //定义一个BillDAO属性，使用对应的方法
    private BillDAO billDAO=new BillDAO();
    //定义一个MenuService的对象，调用该对象的getMenuById()获取price属性
    private MenuService menuService=new MenuService();
    //定义一个DiningTableService属性
    private DiningTableService diningTableService=new DiningTableService();
 //定义一个点餐的方法：
 //1.  生成账单
 //2.更新餐桌状态
    //账单包含的信息有id（null 自增长），billId（uuid生成不需要传参），menuId（从界面传入的参数）,nums(从界面传入的参数)
    //money（菜品*数量，通过计算获取），diningTableId(传入的参数)，billDate(now),state(未结账)
    public boolean orderMenu(int menuId,int nums,int diningTableId){//需要传进来的参数有menuId nums diningTableID
        //UUID生成不重复的账单号
        String billId = UUID.randomUUID().toString();
        //将账单生成到bill表
        //如果成功返回true
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账') ", billId, menuId, nums,
                menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update<=0){
            return false;
        }
        //更新餐桌状态(通过调用DindingTableService的updateDiningTableState（）方法实现)
        return diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }
}