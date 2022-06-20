package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;

import java.util.List;

public class RegistrationInterfaceImpl implements RegistrationInterface {
    @Override
    public boolean addCourse(String courseCode, int studentId, List<Course> studentCourseList) {
        return false;
    }

    @Override
    public boolean dropCourse(String courseCode, int studentId, List<Course> studentCourseList) {
        return false;
    }

    @Override
    public List<Course> viewCourses(int studentId) {
        return null;
    }

    @Override
    public void setRegistrationStatus(int studentId) {

    }

    @Override
    public boolean getRegistrationStatus(int studentId) {
        return false;
    }

    @Override
    public List<Grade> viewGradeCard(int studentId) {
        return null;
    }
}
