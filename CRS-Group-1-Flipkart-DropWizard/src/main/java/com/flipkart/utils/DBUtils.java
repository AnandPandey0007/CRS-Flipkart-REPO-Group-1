package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSRegistration";
    static final String USER = "root";
    static final String PASS = "Blue_176484";
    static Connection connection;
    public static Connection getConnection() {
           try{
            if (connection == null || connection.isClosed()) {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Database connection established...");
            } else {
                return connection;
            }
           } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
        return connection;
        }
}