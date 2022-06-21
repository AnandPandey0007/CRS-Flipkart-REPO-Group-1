package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;
import com.flipkart.constant.*;

import java.util.List;

public interface StudentInterface {
    public int register(String name, String userID, String password, Gender gender, int batch, String branch, String address, String country);
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
    public boolean addCourse(String courseCode, int studentId, List<Course> studentCourseList);

    public boolean dropCourse(String courseCode, int studentId, List<Course> studentCourseList);

    public List<Course> viewCourses(int studentId);

    public void setRegistrationStatus(int studentId);

    public boolean getRegistrationStatus(int studentId);

    public List<Grade> viewGradeCard(int studentId);
}
