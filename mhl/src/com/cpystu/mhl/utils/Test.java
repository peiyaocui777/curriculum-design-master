package com.cpystu.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 崔佩谣
 * @date 2025/4/15 11:32
 * @description: test
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        System.out.println("输入一个字符");
        int c = Utility.readInt(3);//defaultValue默认值，如果直接回车，则返回默认值，否则返回输入的整数
        System.out.println(c);
        //测试JDBCUtilsByDruid
        Connection connection = JDBCUtilsByDruid.getConnection(); //可以连接  已经成功初始胡
    }
}
