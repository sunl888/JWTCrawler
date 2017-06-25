package com.sunlong;

import java.sql.*;

/**
 * Created by Sunlong on 2017/6/24.
 */

public class User extends Thread {
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/";// 根据实际情况变化
    private String dbUser = "root";
    private String dbPass = "root";
    private String dbName = "hello";
    private String parameters = "?characterEncoding=utf8&useSSL=false";
    private String[] data;

    public User() {
        super();
    }

    public User(String[] data) {
        this.data = data;
    }

    public void run() {
        insert();
    }

    public void insert() {
        String sql = "INSERT INTO `users`(`student_id`, `student_name`, `major`, `department`, `class`, `nation`, `place_of_origin`, `date_of_birth`, `political_outlook`, `id_card`, `examinee_number`, `cell_phone_number`, `password`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection cnn = getConn();
        try {
            PreparedStatement preStmt = cnn.prepareStatement(sql);
            preStmt.setString(1, "".equals(data[1]) ? null : data[1]);
            preStmt.setString(2, "".equals(data[2]) ? null : data[2]);
            preStmt.setString(3, "".equals(data[3]) ? null : data[3]);
            preStmt.setString(4, "".equals(data[4]) ? null : data[4]);
            preStmt.setString(5, "".equals(data[5]) ? null : data[5]);
            preStmt.setString(6, "".equals(data[6]) ? null : data[6]);
            preStmt.setString(7, "".equals(data[7]) ? null : data[7]);
            preStmt.setString(8, "".equals(data[8]) ? null : data[8]);
            preStmt.setString(9, "".equals(data[9]) ? null : data[9]);
            preStmt.setString(10, "".equals(data[10]) ? null : data[10]);
            preStmt.setString(11, "".equals(data[11]) ? null : data[11]);
            preStmt.setString(12, "".equals(data[12]) ? null : data[12]);
            preStmt.setString(13, "".equals(data[13]) ? null : data[13]);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cnn = null;
            }
        }
    }

    public String[] getStudent(String student_id) {
        String sql = "select * from users where student_id='" + student_id.trim() + "'";
        String[] student = new String[15];
        Connection cnn = getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = cnn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                student[1] = rs.getString(1);
                student[2] = rs.getString(2);
                student[3] = rs.getString(3);
                student[4] = rs.getString(4);
                student[5] = rs.getString(5);
                student[6] = rs.getString(6);
                student[7] = rs.getString(7);
                student[8] = rs.getString(8);
                student[9] = rs.getString(9);
                student[10] = rs.getString(10);
                student[11] = rs.getString(11);
                student[12] = rs.getString(12);
                student[13] = rs.getString(13);
                student[14] = rs.getString(14);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();//NullPointer错误
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    private Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl + dbName + parameters, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void updateImage(String studentNum, String filePath) {
        String res[] = getStudent(studentNum);
        String sql = "update users set photo='" + filePath + "' where student_id='" + studentNum.trim() + "'";
        //判断数据库里有没有该学生信息
        String[] student = new User().getStudent(studentNum);
        if (null != student[1]) {
            Connection cnn = getConn();
            Statement stmt = null;
            try {
                stmt = cnn.createStatement();
                //这里因为是更新数据，所以不能使用executeQuery()来处理数据，而且更新数据返回值是一个int型而不是ResultSet
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    stmt = null;
                }
            }
        }
    }
}