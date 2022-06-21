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
        students = new HashMap<>(); courses = new HashMap<>();
    }
    @Override
    public int register(String name, String userID, String password, Gender gender, int batch, String branch, String address, String country) {
        Student newStudent = new Student();
        newStudent.setStudentName(name);
        newStudent.setStudentId(userID);
        students.put(userID, newStudent);
        return 0;
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
    public boolean addCourse(String userID, List<Course> studentCourseList) {
        List<Course> existingCourseList;
        if(students.get(userID).getCourses() == null){
            existingCourseList = new ArrayList<>();
        } else {
            existingCourseList = new ArrayList<>(students.get(userID).getCourses());
        }
        for(Course newCourse : studentCourseList){
            existingCourseList.add(newCourse);
        }
        courses.put(userID, existingCourseList);
        return true;
    }

    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> studentCourseList) {
        List<Course> enrolledCourses;
        if(students.get(studentId).getCourses() == null){
            return false;
        } else {
            enrolledCourses = students.get(studentId).getCourses();
        }
        for(Course enrolled : enrolledCourses) {
            if(enrolled.getCourseCode().equals(courseCode)){
                enrolledCourses.remove(enrolled);
            }
        }
        return true;
    }

    @Override
    public List<Course> viewCourses(String studentId) {
        return students.get(studentId).getCourses();
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
