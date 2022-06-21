package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;
import com.flipkart.constant.*;

import java.util.List;

public interface StudentInterface {
    public int register(String name, String userID, String password, Gender gender, int batch, String branch, String address, String country);
    public String getStudentId(String userId);
    public boolean isApproved(String studentId);
    public boolean addCourse(String courseCode, List<Course> studentCourseList);

    public boolean dropCourse(String courseCode, String studentId, List<Course> studentCourseList);

    public List<Course> viewCourses(String studentId);

    public void setRegistrationStatus(String studentId);

    public boolean getRegistrationStatus(String studentId);

    public List<Grade> viewGradeCard(int studentId);
}
