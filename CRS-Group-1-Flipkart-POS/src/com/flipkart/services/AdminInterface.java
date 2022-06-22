package com.flipkart.services;

import com.flipkart.beans.*;

import java.util.List;

public interface AdminInterface {
    public void assignCourse(String courseCode, String professorId);
    public void addCourse(Course course, List<Course> courseList);
    public void deleteCourse(String courseCode, List<Course> courseList);
    public void viewCourses();

    public List<Student> viewPendingStudentApprovals();
    public void approveStudent(int studentId, List<Student> studentList);
    public void addProfessor(Professor professor);
    public List<Professor> viewProfessors();
}
