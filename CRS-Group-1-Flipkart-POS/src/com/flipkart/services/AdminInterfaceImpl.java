package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;

import java.util.List;

public class AdminInterfaceImpl implements AdminInterface{

    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {

    }

    @Override
    public void addCourse(Course course, List<Course> courseList) {

    }

    @Override
    public void approveStudent(int studentId, List<Student> studentList) {

    }

    @Override
    public void addProfessor(Professor professor) {

    }

    @Override
    public void assignCourse(String courseCode, String professorId) {

    }

    @Override
    public List<Course> viewCourses(int catalogId) {
        return null;
    }

    @Override
    public List<Student> viewPendingStudentApprovals() {
        return null;
    }

    @Override
    public List<Professor> viewProfessors() {
        return null;
    }
}
