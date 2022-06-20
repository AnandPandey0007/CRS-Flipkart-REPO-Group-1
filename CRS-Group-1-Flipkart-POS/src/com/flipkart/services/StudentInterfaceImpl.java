package com.flipkart.services;

import com.flipkart.constant.*;

public class StudentInterfaceImpl implements StudentInterface{
    @Override
    public int register(String name, String userID, String password, Gender gender, int batch, String branch, String address, String country) {
        return 0;
    }

    @Override
    public int getStudentId(String userId) {
        return 0;
    }

    @Override
    public boolean isApproved(int studentId) {
        return false;
    }
}
