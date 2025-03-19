package com.cpystu.houserent.view;

import com.cpystu.houserent.domain.House;
import com.cpystu.houserent.service.HouseService;
import com.cpystu.houserent.utils.Utility;

/*
* 这个类要实现的功能
* 1.显示界面
* 2.接收用户的输入
* 3.调用HouseService完成对房屋信息的各种操作
**/
public class HouseView {
    //类似零钱通的界面显示，使用do-while循环
    //定义变量loop控制循环是否结束
    private boolean loop =true;//?属性有默认返回值，变量没有（我不确定）
    //TODO
    /**
     * 是的 类变量有默认值 private boolean loop; ====> 这样写编译器并没有报错
     * 方法周变量没有 需要设置
     */
    //定义变量key接收输入信息
    private char key=' ';//'空格'
    private HouseService houseService = new HouseService(3);//??为什么在这个位置设置数组大小为10（调用时）
    //根据id修改房屋信息
    public void update(){
        System.out.println("---------修改房屋信息--------");
        System.out.print("请输入待修改房屋编号（-1退出）");
        int updateId = Utility.readInt();
        if (updateId==-1){
            System.out.println("---------你放弃修改房屋信息--------");
            return;
        }
        //没有放弃修改，根据输入的id查找对象
        House house = houseService.findById(updateId);//实参是updateId？？
        if (house==null){
            System.out.println("该房屋编号不存在！！");
            return;
        }
        System.out.print("姓名（"+house.getName()+"）:");
        String name = Utility.readString(8, "");//如果用户直接回车表示不修改信息，默认“ ”
        if (!"".equals(name)){//name不是空的时候再修改
            house.setName(name);
        }
        System.out.print("电话（（"+house.getPhone()+"））：");
        String phone = Utility.readString(16, "");
        if (!"".equals(phone)){
            house.setPhone(phone);
        }
        System.out.println("地址("+house.getAddress()+"):");
        String address = Utility.readString(16, "");
        if (!"".equals(address)){
            house.setAddress(address);
        }
        System.out.println("月租（"+house.getRent()+"）：");
        //String rent = Utility.readString(8, "");
        int rent = Utility.readInt(-1);//???为什么是-1
        //if (!"".equals(rent)){
            //house.setRent(Integer.parseInt(rent));//?类型转换了
        if (rent!=-1){
            house.setRent(rent);
        }
        System.out.println("状态("+house.getState()+")：");
        String state = Utility.readString(12, "");
        if (!"".equals(state)){
            house.setState(state);
        }
        System.out.println("---------修改房屋信息成功！！--------");
    }
    //写一个find()查找房屋信息
    public void findHouse() {
        System.out.println("---------查找房屋信息--------");
        System.out.print("请输入你要查找的id：");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if (house!=null){//判断findById返回的值是否是空
            System.out.println(house);
        }else {
            System.out.println("你查找的信息不存在");
        }
    }
    //写一个退出确认的方法
    public void exit() {
        char c = Utility.readConfirmSelection();//Utility方法中有这个循环确认功能，直接调用
        if (c == 'Y') {//判断c的值是Y还是N
            loop = false;
        }
    }

    //编写delHouse（）接收输入的id，调用Service的del方法
    public void delHouse(){
        System.out.println("---------删除房屋信息--------");//先打印界面
        System.out.print("请选择待删除房屋的编号（-1退出）：");
        int delId=Utility.readInt();//接收用户输入的id并赋给delId
        if (delId==-1){
            System.out.println("---------你放弃删除房屋信息--------");
            return;
        }
        //提示用户确认是否删除
        char choice=Utility.readConfirmSelection();//readConfirmSelection()本身就有循环判断，不是Y/N无法退出循环
        if (choice=='Y'){//用户确认删除
            //调用Service中的del()进行删除
            //del()返回一个boolean值，对其进行判断
            if (houseService.del(delId)){
                System.out.println("---------删除房屋信息成功--------");
                return;
            }else {
                System.out.println("---------房屋编号不存在，删除失败--------");
            }
        }else {
            System.out.println("---------你放弃删除房屋信息--------");
        }

    }
    //！！private什么情况下要加
    //编写addHouse()接收输入，创建House对象，调用add方法
    public void addHouse(){
        //打印界面
        System.out.println("---------添加房屋--------");
        System.out.print("姓名： ");
        String name=Utility.readString(6);
        System.out.print("电话： ");
        String phone=Utility.readString(12);
        System.out.print("地址： ");
        String address=Utility.readString(16);
        System.out.print("月租： ");
        int rent=Utility.readInt();
        System.out.print("状态： ");
        String state=Utility.readString(3);
        //得到属性，创建新的House对象！id是系统分配的，用户不能输入
        House newHouse=new House(0,name,phone,address,rent,state);
        //id是系统分配的但是创建对象的时候也要填上
        if (houseService.add(newHouse)){
            System.out.println("------------添加房屋成功------------");
            return;
        }else {
            System.out.println("------------添加房屋失败------------");
        }
    }


    //编写一个listHouses（）显示房屋列表
    public void listHouses(){
        System.out.println("---------房屋列表--------");
        System.out.println("编号\t房主\t电话\t地址\t月租\t状态（已出租/未出租）");//打印表头
        //要调用list方法得到房屋信息，list方法在HouseService类里面，调用不同类的方法需要创建他的对象
        //即 HouseService houseservice=new HouseService(10)
        
       // final var houses = houseService.list();运行报错
         /*java: 找不到符号
          符号:   类 var
          位置: 类 com.cpystu.houserent.view.HouseView*/
        House[] houses = houseService.list();
        //fori快捷键
        for (int i = 0; i < houses.length; i++) {//循环遍历数组信息
            //先判断。如果内容是空的就不显示（内容指的就是houses[i]）
            if (houses[i]==null){//houses是数组名，houses【i】为对应的元素
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("------------房屋列表显示完毕------------\n");

    }
    //1.显示一个主菜单
    public void mainMenu(){
        do {
            System.out.println("------------房屋出租系统--------------");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 显 示 房 屋 列 表");
            System.out.println("\t\t\t6 退       出 ");
            System.out.print("请输入你的选择（1-6）：");
            key= Utility.readChar();//调用工具类获取键盘输入信息
            //判断用户输入的信息并执行对应的操作
            //使用switch-case分支判断
            switch (key){
                case '1'://key的值与case比较
                    addHouse();
                    break;//不要忘记break
                case '2':
                    findHouse();
                    break;//不要忘记break
                case '3':
                    delHouse();
                    break;//不要忘记break
                case '4':
                    update();
                    break;//不要忘记break
                case '5':
                    listHouses();
                    break;//不要忘记break
                case '6':
                    exit();
                    break;//不要忘记break
            }
        }while (loop);//;
    }

}
