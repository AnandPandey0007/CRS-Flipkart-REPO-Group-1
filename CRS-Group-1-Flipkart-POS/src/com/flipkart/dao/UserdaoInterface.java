package com.flipkart.dao;
//import com.flipkart.exception.UserNotFoundException;
/*
  Interface for User Dao Operations
 */
public interface UserdaoInterface {

    /*
     * Method to verify credentials of Users from DataBase
     * @user userId
     * @user password
     * @return Verify credentials operation status
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) ;//throws UserNotFoundException;

    /*
     * Method to get Role of User from DataBase
     * @user userId
     * @return Role
     */
    public String getRole(String userId);


    /*
     * Method to update password of user in DataBase
     * @user userID
     * @user newPassword
     * @return Update Password operation Status
     */
    public boolean updatePassword(String userId,String newPassword);
}