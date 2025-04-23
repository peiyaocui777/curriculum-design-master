package com.cpystu.mhl.service;

import com.cpystu.mhl.dao.DiningTableDAO;
import com.cpystu.mhl.domain.DiningTable;

import java.util.List;
import java.util.Objects;

/**
 * @author 崔佩谣
 * @date 2025/4/16 15:15
 * @description: 业务层，写具体的要求，并调用DAO实现
 */
public class DiningTableService {
    //要使用DAO，先定义一个DiningTableDAO的属性
    private DiningTableDAO diningTableDAO=new DiningTableDAO();
    //Service业务需求要显示所有餐桌的状态，因此要返回的就是所有餐桌的状态
    //用list集合接收DiningTable
    public List<DiningTable> listDiningTable(){//只是查询，没有参数传进来，不需要？占位（占位符是为了防止sql注入攻击）
        return diningTableDAO.queryMulti("select id,state from diningTable", DiningTable.class);
    }

    //该方法通过传入的餐桌id判断餐桌的状态是否为null,不为空才可以预定
    public DiningTable getDiningTableById(int id) {//从view层传进来的id
        //diningTableDAO.querySingle("select * from diningTable where id = ?",DiningTable.class,id);
        //return null;
        Object o = diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
        return (DiningTable) o;
    }

    //该方法通过传入的id更新对应的sql语句的 状态（写死的，不需要传参），预定人，预定电话。返回值为布尔类型
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update//这条语句返回值是int，如果update>0,说明更新成功（true）,否则返回值为false
                = diningTableDAO.update("update diningTable set state='已预定',orderName=?,orderTel=? where id=?", orderName, orderTel, id);
        //TODO 刚刚是 参数数量不正确  需要3个参数  传入了 四个 只需要 sql 和 参数 (?) 为什么不需要类型  是因为 更新 不要返回值 不接受  默认返回的int 不需要转换

        return update > 0;//相当于return true；
        //return true;//不能这样写 这样是写死了
    }

    //编写方法，更新餐桌状态
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

}
