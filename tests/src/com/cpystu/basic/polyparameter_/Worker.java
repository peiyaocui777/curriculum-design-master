package com.cpystu.basic.polyparameter_;

//import jdk.nashorn.internal.objects.annotations.Getter;

public class Worker extends Employee {
    public Worker(String name, double salary) {
        super(name, salary);//生成构造方法
    }

    public Worker() {
    }

    //普通员工类
    public void work(){
        System.out.println("普通员工正在工作...");
    }
    public double getAnnual(double sal){
        return 12*sal;
    }

}
