package com.cenyol.study.utils;

import java.sql.*;

/**
 * Created by cenyol on 19/04/2017.
 */
public class MySQL {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private final String USER = "root";
    private final String PASS = "7ujMko0";
    private Connection connection = null;

    public void insert(String string){
        this.connect();
        String sql = "insert test(text) values(?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,string);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                pst.close();
                this.connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
    public void query(){
        this.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = connection.createStatement();
            String sql = "SELECT id, text FROM test";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id  = rs.getInt("id");
                String text = rs.getString("text");

                System.out.print("ID: " + id);
                System.out.println(", 内容: " + text);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                rs.close();
                stmt.close();
                this.connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void connect(){
        if (this.connection == null){
            try {
                Class.forName(JDBC_DRIVER);
                this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
