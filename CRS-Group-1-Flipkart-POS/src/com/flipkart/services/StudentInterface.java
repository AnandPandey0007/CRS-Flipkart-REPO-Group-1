package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;
import com.flipkart.constant.*;

import java.sql.SQLException;
import java.util.List;

public interface StudentInterface {
    public void register(String userID, String name);
    public String getStudentId(String userId);
    public boolean isApproved(String studentId);

    public void addCourse(String studentId, String courseCode) throws SQLException;

    public void dropCourse(String studentId, Course droppedCourse);

    public List<Course> viewCourses(String studentId);

    public void setRegistrationStatus(String studentId);

    public boolean getRegistrationStatus(String studentId);

    public List<Grade> viewGradeCard(int studentId);
}
