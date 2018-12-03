package com.zfsoft.boot.zhjx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OracleConnectionTest {

    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("OracleDriver Loading error！Please check jar of Oracle import ");
            e.printStackTrace();
        }
        String url = "jdbc:oracle:" + "thin:@10.71.19.133:1521:orcl";
        String user="ZFTAL_BOOT_SIMPLE";
        String password="ZFTAL_BOOT_SIMPLE";
        try {
            connection= DriverManager.getConnection(url, user, password);
            System.out.println("Oracle successful!");
        } catch (SQLException e) {
            System.out.println("Oracle link failed！Please check username and password");
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
