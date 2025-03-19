package com.cpystu.basic;

/*
 * 定义一个updatePrice（）方法，
 * 实现更改某某本书的价格
 * 如过price>150,则改为150，如果价格>100
 * 则改为100，否则不变*/
class BookPrice {
    //类名：BookPrice
    //属性：书名 价格
    //方法：updatePrice
    //返回值 void（？？没有返回值吗 难道返回的不是价格）
    //参数：无参(为空)（this调用方法的时候，this代表的是这个对象所以形参可以是空？？？不理解）
    public static void main(String[] args) {
        //在BookPrice类里面调用Book类
        Book book1 = new Book("西游记", 421);
        book1.info();
        double price = book1.updatePrice();
        book1.info();
    }
}

class Book {
    String name;
    double price;

    //构造方法对其初始化（方法名与类名保持一致）
    public Book(String name, double price) {//构造方法没有返回类型（如void）吗？？
        this.name = name;
        this.price = price;
    }

    public double updatePrice() {
        if (this.price > 150) {
            this.price = 150;
            return price;
        }else if (this.price>100){
            this.price=price;
            return price;
        }
        return price;
    }
    //显示书籍情况的方法
    public void info(){
        System.out.println("书名："+this.name+"价格："+this.price);
    }
}
