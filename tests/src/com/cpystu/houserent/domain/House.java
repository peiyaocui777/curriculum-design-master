package com.cpystu.houserent.domain;

/*
  House类在domain包里面
* House的对象就表示一个房屋信息
* */
public class House {
    public House(int id, String name, String phone, String address, int rent, String state) {
        this.id = id;
        Name = name;
        this.phone = phone;
        this.address = address;
        this.rent = rent;
        this.state = state;
    }
    public House() {

    }

    public int getId() {
        return id;
    }

    //属性先根据界面上的内容确定，
    // 写项目的过程中有需要再添加
    //编号 房主 电话 地址 月租 状态（已出租/未出租）
    private int id;//ctrl+alt+l调整格式
    private String Name;
    private String phone;
    private String address;
    private int rent;
    private String state;

    //添加构造方法（构造器）和getter setter方法
    //alt+Insert快捷键->generate（生成）->construct(构造)
//添加构造方法

    //为了方便的输出对象信息，我们实现toString方法
    //？？toString方法需要回去看一看

    @Override
    public String toString() {//调整成我们需要的格式
       /* return  id +
                "\t\t" + Name +
                "\t" + phone +
                "\t" + address +
                "\t" + rent +
                "\t" + state ;
        //*/
        return id+"\t\t"+Name+"\t"+phone+"\t\t"+address+"\t"+rent+"\t"+state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

/*    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }*/
}
