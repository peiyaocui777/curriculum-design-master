package com.cpystu.houserent.utils;

public class TestUtility {
    public static void main(String[] args) {
        //测试utils
        //？？不创建对象就调用方法 可以使用 TODO
        //TODO 这样写 明显
        //！！static静态方法可以通过类名（类名.方法）直接调用

        //TODO  相关知识点  这样写 明显
        /*
        静态方法---类加载时就被分配---不需要创建对象--通过类名调用
        工具类---只包含静态方法--不需要创建对象--方便调用=====>（后面项目多工具类）
         */


        //1.要求输入一个字符串，长度不大于三
//        String s=Utility.readString(3);
//        System.out.println("s="+s);
        String s2=Utility.readString(10,"cpy");
        //ctrl+B定位源码
        //ctrl+alt+⬅返回
        System.out.println(s2);
        //这个测试没报错但是也没运行成功
    }
}
