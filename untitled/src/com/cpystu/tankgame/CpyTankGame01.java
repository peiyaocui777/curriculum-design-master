package com.cpystu.tankgame;

import javax.swing.*;

public class CpyTankGame01 extends JFrame {
    MyPanel mp = null;//这条语句要放在CpyTankGame01类里面构造器才可以直接使用

    public static void main(String[] args) {
        new CpyTankGame01();
    }

    public CpyTankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
