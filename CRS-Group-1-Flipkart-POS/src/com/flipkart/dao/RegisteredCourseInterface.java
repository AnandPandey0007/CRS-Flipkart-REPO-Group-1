package com.flipkart.dao;

import java.sql.SQLException;

public interface RegisteredCourseInterface {
    public void addCourse(String studentId, String professorId, String courseID) throws SQLException;
}
