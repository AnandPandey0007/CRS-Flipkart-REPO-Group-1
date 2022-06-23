package com.flipkart.services;

import com.flipkart.dao.UserDAOOperation;

import java.sql.SQLException;

public class UserInterfaceImpl implements  UserInterface{
    UserDAOOperation userDAOOperation=new UserDAOOperation();
    @Override
    public boolean loginUser(String userId, String password) throws Exception {
     return userDAOOperation.verifyCredentials(userId,password);
    }
}
