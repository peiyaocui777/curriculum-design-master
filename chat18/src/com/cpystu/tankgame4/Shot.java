package src.com.cpystu.tankgame4;

import lombok.Data;

/*
 * 射击子弹的类
 * 1.是一个线程
 * 2.有x y 坐标记录子弹位置
 * 3.有derict属性，子弹发射的方向
 * 4.speed属性，子弹的速度
 * 5.isleave属性，记录子弹生存状况*/
@Data
public class Shot implements Runnable {
    //这里没有把属性设置成private，因为private还需要set get方法，后面可以尝试用@Getter @Setter
    int x;
    int y;
    int direct;
    int speed;//子弹的速度是2个像素，！可以试着做成变量
    boolean isLive = true;
    //构造器！！

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        speed = 2;
        //run方法中实现子弹的发射
        while (true) {//不停的发射子弹，直到打中敌人/达到边界 子弹消失（退出）
            //线程休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //有四个方向，用switch case判断一下子弹的方向
            switch (direct) {
                case 0://向上
                    y -= speed;//以speed的速度向上移动
                    break;
                case 1://向右
                    x += speed;//以speed的速度向右移动
                    break;
                case 2://向xia
                    y += speed;//以speed的速度向xia移动
                    break;
                case 3://向zuo
                    x -= speed;//以speed的速度向左移动
                    break;
            }
            System.out.println("x=" + x + "y=" + y);//用+号拼接
            if (!(x > 0 && x < 1000 && y > 0 && y <= 750)) {//子弹出界
                //修改子弹的状态为false，表示子弹已销毁
                isLive = false;
                System.out.println("子弹已被销毁！");
                break;
            }
        }
    }
}
