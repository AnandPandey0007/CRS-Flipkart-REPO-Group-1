package com.flipkart.utils;
import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRSRegistration";
    static final String USER = "****";
    static final String PASS = "****";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Database connection established...");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}