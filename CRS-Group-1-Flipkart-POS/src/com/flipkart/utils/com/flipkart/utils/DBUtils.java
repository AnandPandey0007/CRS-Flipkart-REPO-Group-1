package com.flipkart.utils;

import com.flipkart.constant.SQLQueriesConstants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    public static void main(String[] args) {
        getConnection();
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TestDB";
    static final String USER = "user1";
    static final String PASS = "user1@";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            try {
//                System.out.println("Database connection success...");
//
//                PreparedStatement statement = connection.prepareStatement("select * from course where professorId='1';");
//                System.out.println("query:  " + statement);
//                ResultSet rs = statement.executeQuery();
//                System.out.println("size:    " + rs.getFetchSize());
//                while (rs.next()) {               // Position the cursor                  4
//                    String x = rs.getString(1);        // Retrieve the first column value
//                    String x1 = rs.getString(2);      // Retrieve the first column value
//                    System.out.println("Employee number = " + x +
//                            "Phone number = " + x1);
//                    // Print the column values
//                }
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

            return connection;
        }
    }
}