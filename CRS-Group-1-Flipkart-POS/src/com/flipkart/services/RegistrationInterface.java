package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;

import java.util.List;

public interface RegistrationInterface {
    public boolean addCourse(String courseCode, int studentId, List<Course> studentCourseList);

    public boolean dropCourse(String courseCode, int studentId, List<Course> studentCourseList);

    public List<Course> viewCourses(int studentId);

    public void setRegistrationStatus(int studentId);

    public boolean getRegistrationStatus(int studentId);

    public List<Grade> viewGradeCard(int studentId);

}
