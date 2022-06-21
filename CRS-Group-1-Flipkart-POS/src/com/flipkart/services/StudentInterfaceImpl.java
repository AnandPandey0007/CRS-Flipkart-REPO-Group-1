package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;
import com.flipkart.constant.*;

import java.util.List;

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
