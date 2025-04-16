package com.cpystu.mhl.service;

import com.cpystu.mhl.dao.DiningTableDAO;
import com.cpystu.mhl.domain.DiningTable;

import java.util.List;

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
}
