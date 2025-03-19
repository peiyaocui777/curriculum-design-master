package com.cpystu.basic;

public class TestBookPrice {
    //在同一个类里面调用方法，与在类1中调用类2里面的方法有什么区别？？
    String name;
    double price;
    public TestBookPrice(String name,double price){
        this.name=name;
        this.price=price;
    }
    public TestBookPrice(){

    }
    public void updatePrice(){
        if (this.price>150){
            this.price=150;
        }else if (this.price>100){
            this.price=100;
        }else {
            this.price=price;
        }
    }
    private void info(){
        System.out.println("书名是："+this.name+"价格是："+this.price);

    }
    //主方法 调用
    public static void main(String[] args) {
        TestBookPrice book1 = new TestBookPrice("海底两万里",145);
        book1.info();
        book1.updatePrice();
        book1.info();
    }
}
