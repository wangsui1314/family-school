package com.bjwk.controller.publics.nearman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class InsertTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://58.87.76.126:4406/family-school?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        Class.forName(name);//指定连接类型 
        conn = DriverManager.getConnection(url, user, password);//获取连接 
        if (conn != null) {
            System.out.println("获取连接成功");
           new InsertTest().insert(conn);
        } else {
            System.out.println("获取连接失败");
        }

    }

    public  void insert(Connection conn) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO course_video_bank (coverMapImage,title,lecturer,contentValidity," +
                "starLevel,video,videoTime,packageNum,page,isCharge,yuan,category) VALUES ";
        int po = 0;
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句

            // 外层循环，总提交事务次数
            for (int i = 1; i <= 1000; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 1000; j++) {
                    // 构建SQL后缀
                    suffix.append(
                            "('https://www.liqitian.com/attachment/20180512/056fdc9d740242a98af7fab71a36d8d5.png'," +
                                    "'测试title第" + i + "个','测试讲师第" + i + "个'" +
                                    ",'内容简介：学的不只是技术，更是梦想'" + ",5,5,10,'1223wqeqw1',1,0,5,'"+getCategrouid()+"'" + "),");
                    po++;
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
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println(po+"条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }

    String getCategrouid() {
        String str = "0,1l,1m,2l,2m,3l,3m,3e,3p,4l,4m,4e,4p,4h,5l,5m,5e,5p,5h,5g,6l,6m,6e,6p,6h,6g,7l,7m,7e,7p,7h,7g,7b,8l,8m,8e,8p,8h,8g,8b,8ph,9l,9m,9e,9p,9h,9g,9b,9ph,10l,10m,10e,10p,10h,10g,10b,10ph,10c,11l,11m,11e,11p,11h,11g,11b,11ph,11c,12l,12m,12e,12p,12h,12g,12b,12ph,12c";
        String[] strs = str.split(",");
        return strs[(int) (1 + Math.random() * (strs.length - 1))];

    }
}

