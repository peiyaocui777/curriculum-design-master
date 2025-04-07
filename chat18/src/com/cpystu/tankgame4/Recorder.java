package src.com.cpystu.tankgame4;

import lombok.Getter;

import java.io.*;
import java.util.Objects;
import java.util.Vector;

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
    // private static FileWriter fw = null;//todo FileWriter:节点流 ??FileWriter可以不声明吗？
    private static BufferedWriter bw = null;//todo BufferedWriter:处理流
    //定义一个BufferReader用于读取文件
    private static BufferedReader br = null;
    //定义一个存放文件的路径
    private static String recordFile="d:\\myRecord.txt";
    //定义一个Node类的Vector集合，用于存放敌人坦克的所有信息（方便通过遍历得到/Reader敌人坦克）
    private static Vector<Node> nodes = new Vector<>();


    //写一个方法，用于将nodes中的敌人坦克信息从recordFile文件里面取出来
    //该方法在选择“继续上局游戏”时被调用
    public static Vector<Node> getNodesAndEnemyTanksRec() {//返回一个Vector<Node>

        //从recordFile取出信息
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());//调用方法返回的值赋给allEnemyTankNum，readLine返回一个String，需要用Integer.parseInt()转换成int类型
            //使用while循环读取坦克信息，生成vector
            //1.定义一个String，初始化为空串，表示读到的一行数据(按行读取)
            String line = "";//结果为100 20 50的字符串
            while ((line = br.readLine()) != null) {//读取到的数据赋给line
                //将得到的一行带有“ ”的字符串按“ ”分割成数组，方便通过数组下标得到每个值
                String[] xyd = line.split(" ");//todo 怎么就分割成数组了
                //new一个Node对象，通过下标将字符串数组的值分别作为Node构造器的X Y D 传进去（需要转换类型）
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);//将node对象放到集合里面，方便管理
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;
    }

    /**
     * 敌方坦克
     */
    public static Vector<EnemyTank> enemyTanks;//static修饰后，静态属性，可以通过类名.setEnemyTanks调用

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {//todo 只有vector集合是静态属性 set方法才能是静态的吗
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getRecordeFile() {

        return recordFile;//返回记录文件的路径
    }
    // public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
    // this.enemyTanks = enemyTanks;
    // }

    //增加一个方法，当游戏退出时，将allEnemyTankNum 保存到recordFile
    public void keepRecord() {

            //todo BufferedWriter与FileWriter的关系
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            if (enemyTanks.size() == 3) {
                Integer sum = allEnemyTankNum < enemyTanks.size() ? 0 : allEnemyTankNum; //这一行的意思就是 3 《 3吗 不小于 为 0
                bw.write(sum + "\r");//主需要换行 不需要/t
            } else {
                bw.write(allEnemyTankNum + "\r");//主需要换行 不需要/t
            }
            //循环遍历enemyTanks数组里面的坦克，判断是否存活，存活就将他的x y 方向写入MyRecorder文件
           /* for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {

                }
            }*/

            if (!Objects.isNull(enemyTanks)) {
                for (EnemyTank enemyTank : enemyTanks) {
                    if (enemyTank.isLive) {
                        String s = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                        bw.write(s + "\r");
                        //bw.newLine();
                        System.out.println(s);
                        //System.out.println("存活的敌方坦克1x="+enemyTank.getX()+"y="+enemyTank.getY()+"方向="+enemyTank.getDirect());
                    }
                }
            }
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
