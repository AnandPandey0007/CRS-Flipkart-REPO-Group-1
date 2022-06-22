package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Grade;
import com.flipkart.beans.Student;
import com.flipkart.constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInterfaceImpl implements StudentInterface{

    Map<String, Student> students;
    Map<String, List<Course>> courses;

    public void createStudent(){
        students = new HashMap<>();
        courses = new HashMap<>();
    }
    @Override
    public void register(String userID, String name) {
        createStudent();
        Student newStudent = new Student();
        newStudent.setStudentName(name);
        newStudent.setStudentId(userID);
        students.put(userID, newStudent);
    }

    @Override
    public String getStudentId(String userId) {
        return userId;
    }

    @Override
    public boolean isApproved(String userID) {
        return students.get(userID).isApproved();
    }

    @Override
    public void addCourse(String studentId, Course newCourse) {
        students.get(studentId).addCourses(newCourse);
    }

    @Override
    public void dropCourse(String studentId, Course course) {
        students.get(studentId).removeCourses(course);
    }

    @Override
    public List<Course> viewCourses(String studentId) {
        List<Course> courseList=students.get(studentId).getCourses();
        for(int i=0; i<courseList.size(); i++){
            System.out.println("Course Code: "+courseList.get(i).getCourseCode()+" "+"Course Name: "+courseList.get(i).getCourseName());
        }
        return courseList;
    }

    @Override
    public void setRegistrationStatus(String studentId) {
         students.get(studentId).setApproved(true);
    }

    @Override
    public boolean getRegistrationStatus(String studentId) {
        return students.get(studentId).isApproved();
    }
//
    @Override
    public List<Grade> viewGradeCard(int studentId) {
        return null;
    }

}
