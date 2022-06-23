package com.flipkart.dao;

import java.sql.SQLException;

public interface UserDAOInterface {
    public boolean verifyCredentials(String userId, String password) throws Exception;
}
