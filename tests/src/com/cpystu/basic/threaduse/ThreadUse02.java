package com.cpystu.basic.threaduse;

public class ThreadUse02 {
    public static void main(String[] args) {
        dog dog = new dog();
        Thread thread = new Thread(dog);
        thread.start();
    }
}
class dog implements Runnable{
int count=0;
    @Override
    public void run() {
        while (true) {
            System.out.println("小狗汪汪叫" + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count==10){
                break;
            }
        }
    }
}
