package com.flipkart.dao;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOOperation implements UserDAOInterface {
    @Override
    public boolean verifyCredentials(String userId, String password) throws Exception {
        Connection connection = DBUtils.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new Exception(userId);
            } else if(password.equals(resultSet.getString("password"))) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException ex) {
            System.out.println("Something went wrong, please try again! "+ ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
}
