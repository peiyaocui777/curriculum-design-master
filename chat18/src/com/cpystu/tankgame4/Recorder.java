package src.com.cpystu.tankgame4;

import lombok.Data;
import lombok.Getter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 崔佩谣
 * @date 2025/4/3 0:30
 * @description: 该类用于记录相关信息，和文件交互
 */
public class Recorder {
    //定义一个私有的 静态变量，记录击毁敌方坦克的数量
    @Getter
    //@Data todo 报错了
    private static int allEnemyTankNum = 0;
    //定义IO相关的对象,用于写数据到文件中
    private static FileWriter fw = null;//todo FileWriter:节点流
    private static BufferedWriter bw = null;//todo BufferedWriter:处理流
    //定义一个存放文件的路径
    private static String recordFile="d:\\myRecord.txt";

    //增加一个方法，当游戏退出时，将allEnemyTankNum 保存到recordFile
    public static void keepRecord(){

            //todo BufferedWriter与FileWriter的关系
        try {
            bw=new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\t");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw!=null){
                    bw.close();//关流
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
    //写一个方法，当敌方坦克被击毁时，allEnemyTankNum++
    public static void addAllEnemyTankNum(){//工具类
        Recorder.allEnemyTankNum++;
    }
}
