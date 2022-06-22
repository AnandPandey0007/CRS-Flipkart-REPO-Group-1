package com.flipkart.beans;

import java.util.List;

public class Student {

    private String studentId;

    private String studentName;

    private String password;

    private boolean isApproved;

    private List<Course> courseList;

    public List<Course> getCourses() {
        return courseList;
    }

    public void addCourses(Course course) {
        courseList.add(course);
    }

    public void removeCourses(Course course){
        courseList.remove(course);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

}
