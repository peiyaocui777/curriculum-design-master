package com.cpystu.tankgame4;

import java.util.Vector;

public class EnemyTank extends Tank {//敌人坦克
    //给敌人坦克一个shot属性，这个属性的类型是vector<>类型（原因：有多个shot且在线程中使用）
    Vector<Shot> shots = new Vector<>();//创建了shots集合，此时集合中还没有数据，即shots只是有了名字，还没被创建

    //当Enemy对象被绘制时shot有坐标值
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
