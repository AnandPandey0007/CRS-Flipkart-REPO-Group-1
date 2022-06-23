package com.flipkart.constant;

public enum Role {
    STUDENT,
    PROFESSOR,
    ADMIN;

    public static Role stringToRole(String role)
    {
        Role userRole=null;
        if(role.equalsIgnoreCase("ADMIN"))
            userRole=Role.ADMIN;
        else if(role.equalsIgnoreCase("PROFESSOR"))
            userRole=Role.PROFESSOR;
        else if(role.equalsIgnoreCase("STUDENT"))
            userRole=Role.STUDENT;
        return userRole;
    }

}
