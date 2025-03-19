package com.cpystu.basic.polyparameter_;

public class Employee {
    private String name;
    private double salary;
    //计算年工资的方法
    public double getAnnual(double salary){
        /*this.salary=12*salary;
        return salary;*/
        return 12*salary;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
