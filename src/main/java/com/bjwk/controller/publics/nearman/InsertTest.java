package com.bjwk.controller.publics.nearman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
 
import com.mysql.jdbc.PreparedStatement;
 
public class InsertTest {
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://www.wangsui.top/family-school"; 
        final String name = "com.mysql.jdbc.Driver"; 
        final String user = "root"; 
        final String password = "root"; 
        Connection conn = null; 
        Class.forName(name);//指定连接类型 
        conn = DriverManager.getConnection(url, user, password);//获取连接 
        if (conn!=null) {
            System.out.println("获取连接成功");
            insert(conn);
        }else {
            System.out.println("获取连接失败");
        }
 
    }
    public static void insert(Connection conn) {
        // 开始时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO jjxy_area (area_lng,area_lat,area_name,user_id) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement  pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" +j+"','"+i*j+"','123456'"+","+i+"" +"),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                System.out.println(sql);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("10万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}

