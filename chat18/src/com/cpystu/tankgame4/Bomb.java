package src.com.cpystu.tankgame4;

public class Bomb {
    int x,y;//炸弹的坐标，没有初始值 给一个构造器，后面通过构造器设置x y
    int life=100;//生命值，不同的生命值显示不同的爆炸效果，写一个方法lifeDown实现不同效果之间的切换
    //TODO 子弹击中坦克直接使用lifeDown方法顺序展示三张图片是否一样可以实现动态爆炸效果
    boolean isLive=true;//生存状态

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if (life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
