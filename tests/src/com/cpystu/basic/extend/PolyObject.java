package com.cpystu.basic.extend;

import com.cpystu.basic.extend.Animal;
import com.cpystu.basic.extend.Cat;
import com.cpystu.basic.extend.Dog;

public class PolyObject {
public static void main(String[] args) {
//体验对象多态特点
//animal 编译类型就是 Animal , 运行类型 Dog
Animal animal = new Dog();
//因为运行时 , 执行到改行时，animal 运行类型是 Dog,所以 cry 就是 Dog 的 cry
animal.cry(); //小狗汪汪叫
//animal 编译类型 Animal,运行类型就是 Cat
    System.out.println(animal.getClass()+"在叫");
animal = new Cat();
animal.cry(); //小猫喵喵叫
}
}