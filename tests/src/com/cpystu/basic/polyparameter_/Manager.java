package com.cpystu.basic.polyparameter_;

import javax.naming.Name;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(double bonus) {
        this.bonus = bonus;
    }

    public void manage(){
        System.out.println("经理"+ getName() + "正在管理");
    }
    public double getAnnual(double sal){
        return 12*sal+bonus;
    }
}
