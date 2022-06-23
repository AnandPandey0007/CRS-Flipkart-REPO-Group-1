package com.flipkart.services;

import java.sql.SQLException;

public interface UserInterface {
    public boolean loginUser(String userId, String password) throws Exception;
}
