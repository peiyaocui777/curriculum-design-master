package com.cpystu.basic.polyparameter_;

public class PloyParameter {
    //定义员工类Employee，包含姓名和月工资（private）
    //以及计算年工资getAnnual的方法。
    //普通员工和经理继承了员工，经理类多了奖金bones属性
    //和管理manage方法
    //测试类中添加一个方法showEmpAnnual(Employee e),实现获取任何员工
    //对象的年工资，并在main放发中调用改方法（e.getAnnual()）
    //测试类中添加一个方法，testWork。普通员工调用work（）,经理调用manage（）
    public static void main(String[] args) {
        //new两个对象传入实参
        Worker hana = new Worker("hana", 2500);
        Manager naomi = new Manager("naomi" , 5000.0,20000);
        //创建一个PloyParameter类的对象，用来调用该类的showEmpAnnual（）
        PloyParameter ployParameter = new PloyParameter();
        ployParameter.showEmpAnnual(hana);//showEmpAnnual()的参数是？
        ployParameter.showEmpAnnual(naomi);
        ployParameter.testWork(hana);
    }
    public void showEmpAnnual(Employee e){
        System.out.println("员工"+e.getName()+"工资为"+e.getAnnual(e.getSalary()));//传入的是哪个子类就得到哪个子类的工资
    }
    public void testWork(Employee employee){//testWork()形参列表里的类名是固定的，employee是个代号可以随便换
        if (employee instanceof Worker){//判断对象运行类型的句式：对象名 instanceof 类名（没有.）
            ((Worker) employee).work();//向下转型
        } else if (employee instanceof Manager) {
            ((Manager) employee).manage();
        }else {
            System.out.println("不做处理");
        }
    }
}

