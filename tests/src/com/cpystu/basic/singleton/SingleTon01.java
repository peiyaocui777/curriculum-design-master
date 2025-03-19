package com.cpystu.basic.singleton;

public class SingleTon01 {
    //单例模式-饿汉模式
    //单例模式实现步骤
    //1.构造器私有化
    //2.在构造器内部创建对象
    //3.提供一个公共的方法访问这个对象
    public static void main(String[] args) {
        Girlfriend instance = Girlfriend.getInstance();
        System.out.println(instance);
    }
    static class Girlfriend{
        private String name;
        //private static String Girlfriend="小花";错误的
        private static Girlfriend gf=new Girlfriend("小花");
        private Girlfriend(String name) {
            System.out.println("构造器被调用。。");
            this.name=name;
        }
        public  static Girlfriend getInstance(){
            return gf;

        }

        @Override
        public String toString() {
            return "Girlfriend{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
