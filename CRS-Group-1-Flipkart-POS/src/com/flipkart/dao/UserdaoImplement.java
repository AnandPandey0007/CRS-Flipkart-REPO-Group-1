package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

//import com.flipkart.constant.SQLQueriesConstants;
//import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;

/*
Class to implement User dao Operations
 */
public class UserdaoImplement implements UserdaoInterface {
    private static volatile UserdaoImplement instance = null;
    private static Logger logger = Logger.getLogger(UserdaoImplement.class);

    /*
      Default Constructor
    */
    private UserdaoImplement() {

    }

    /*
     Method to make UserdaoImplementation Singleton
     @return
     */
    public static UserdaoImplement getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (UserdaoImplement.class) {
                instance = new UserdaoImplement();
            }
        }
        return instance;
    }


     /*
     * Method to verify credentials of Users from DataBase
     * @user userId
     * @user password
     * @return Verify credentials operation status
     * @throws UserNotFoundException
     */
    @Override
    public boolean verifyCredentials(String userId, String password) {//throws UserNotFoundException {
        Connection connection = DBUtils.getConnection();
        try {
            //open db connection
            PreparedStatement preparedStatement = connection.prepareStatement("select password from user where userId = ?");
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next())
                throw new UserNotFoundException(userId);
            else if (password.equals(resultSet.getString("password"))) {
                return true;
            } else {
                return false;
            }

        }
//        catch(SQLException ex)
//        {
//            logger.info("Something went wrong, please try again! "+ ex.getMessage());
//        }
        finally
        {
            try {
                connection.close();
            }
//            catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
        return false;

    }
    /*
     * Method to update password of user in DataBase
     * @user userID
     * @user newPassword
     * @return Update Password operation Status
     */
    @Override
    public boolean updatePassword(String userId, String newPassword) {
        Connection connection=DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update user set password=? where userId = ? ");

            statement.setString(1, newPassword);
            statement.setString(2, userId);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else
                return false;
        }
//        catch(SQLException e)
//        {
//            logger.error(e.getMessage());
//        }
        finally
        {
            try {
                connection.close();
            }
//            catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
        return false;
    }
    /**
     * Method to update password of user in DataBase
     * @param userID
     * @return Update Password operation Status
     */
    @Override
    public boolean updatePassword(String userID) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method to get Role of User from DataBase
     * @user userId
     * @return Role
     */
    @Override
    public String getRole(String userId) {
        Connection connection=DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select role from user where userId = ? ");
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                return rs.getString("role");
            }

        }
//        catch(SQLException e)
//        {
//            logger.error(e.getMessage());
//        }
        finally
        {
            try {
                connection.close();
            }
//            catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
        return null;
    }



}




