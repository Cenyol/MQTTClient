package com.cenyol.study.utils;

import java.sql.*;
import com.google.gson.Gson;

/**
 * Created by cenyol on 19/04/2017.
 */
public class MySQL {
    // 部分参考：http://www.cnblogs.com/aniuer/archive/2012/09/10/2679241.html
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://mysql.cenyol.com/agriot";
    private final String USER = "cenyol";
    private final String PASS = "f4rnf8c8s0mx03kd94fcs12s";
    private Connection connection = null;

    public void insert(String jsonString){
        this.connect();
        String sql = "insert data_record(sensor_num, object_id, object_type, sensor_type_id,sensor_value,collect_time)" +
                " values(?,?,?,?,?,?)";
        PreparedStatement pst = null;
        Gson gson = new Gson();
        SensorData[] sensorDatas = gson.fromJson(jsonString, SensorData[].class);

        // 暂时先保存第一条数据，不为别的，懒。
        SensorData sensorData = sensorDatas[0];
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, sensorData.getNum());
            pst.setInt(2, sensorData.getObject_id());
            pst.setInt(3, sensorData.getObject_type());
            pst.setInt(4, sensorData.getType());
            pst.setDouble(5, sensorData.getValue());
            pst.setInt(6, sensorData.getTimestamp());
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
