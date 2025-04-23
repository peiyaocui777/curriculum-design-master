package com.cpystu.mhl.view;

import com.cpystu.mhl.domain.DiningTable;
import com.cpystu.mhl.service.DiningTableService;
import com.cpystu.mhl.utils.Utility;

import java.net.SocketTimeoutException;
import java.util.zip.DeflaterOutputStream;

/**
 * @author 崔佩谣
 * @date 2025/4/16 15:57
 * @description: 显示预定餐桌的界面，包含预定功能
 * 这个类里面主要写界面显示的内容
 * 预定餐桌其实包含了对餐桌状态的查询判断，如判断输入的餐桌编号是否存在，餐桌状态是否空闲，以及预定后
 * 更新餐桌状态等的业务要写在DindingTableService层
 *
 */
public class OrderTableView {
    private DiningTableService diningTableService = new DiningTableService();
    boolean Loop=true;
    //编写方法OrderTable（），返回一个布尔值
    public void orderTable() {
        //先把界面打印出来
        while (Loop) {
            System.out.println("========预定餐桌========");
            System.out.print("请输入要预定的餐桌编号（-1退出）：");
            int orderId = Utility.readInt();
            if (orderId == -1) {
                System.out.println("========取消预定========");
                Loop = false;
                break;
            }
            char choice = Utility.readConfirmSelection();
            if (choice == 'Y') {
                //使用getDiningTableById()判断orderId是否存在，以及是否可以预定
                DiningTable diningTable = diningTableService.getDiningTableById(orderId);
                if (diningTable == null) {
                    System.out.println("========该餐桌不存在========");
                    return;
                }
                if (!"空".equals(diningTable.getState())) {
                    System.out.println("========该餐桌已被占用========");
                    return;
                }
                System.out.print("预定人名字：");
                String orderName = Utility.readString(50);
                System.out.print("预定人电话：");
                String orderTel = Utility.readString(50);
                //写入数据库
                if (diningTableService.orderDiningTable(orderId, orderName, orderTel)){
                    //orderDiningTable返回值为布尔类型的 判断真假
                    System.out.println("========预定成功========");
                    return;
                }else{
                    System.out.println("========预定失败========");
                    return;
                }
            }else {
            System.out.println("========取消预定========");
            return;
            }
        }
    }


}
