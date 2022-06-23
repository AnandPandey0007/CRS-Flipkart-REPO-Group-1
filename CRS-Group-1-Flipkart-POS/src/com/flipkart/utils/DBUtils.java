package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static  String DB_URL = "jdbc:mysql://localhost:3306/CRSRegistration";
    private static  String USERNAME = "root";
    private static  String PASSWORD = "Blue_176484";
    private static Connection connection;
    public static void setupConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()) {
            setupConnection();
        }
        return connection;
    }
}
