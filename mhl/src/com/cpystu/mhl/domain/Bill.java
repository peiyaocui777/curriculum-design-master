package com.cpystu.mhl.domain;

import java.util.Date;

/**
 * @author 崔佩谣
 * @date 2025/4/21 17:21
 * @description: JavaBean 与bill表对应
 */
public class Bill {
    /*id INT PRIMARY KEY AUTO_INCREMENT,#自增主键
    billId VARCHAR(50) NOT NULL DEFAULT '',#账单号可以根据自己的规则生成UUID
    menuId INT NOT NULL DEFAULT 0,#菜品编号
    nums INT NOT NULL DEFAULT 0,#每份菜点了多少份
    money DOUBLE NOT NULL DEFAULT 0,#金额
    diningTableId INT NOT NULL DEFAULT 0,#餐桌号
    billDate DATETIME NOT NULL,#订单日期
    state VARCHAR(50) NOT NULL DEFAULT ''#状态 ’未结账’，‘已经结账‘，'挂单’.'现金',‘z*/
    private Integer id;
    private String billId;
    private Integer menuId;
    private Double money;
    private Integer diningTableId;
    private Date billdate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer menuId, Double money, Integer diningTableId, Date billdate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billdate = billdate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
