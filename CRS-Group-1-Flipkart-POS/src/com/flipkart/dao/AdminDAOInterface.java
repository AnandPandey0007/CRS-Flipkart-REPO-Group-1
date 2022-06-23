package com.flipkart.dao;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAOInterface {
    public void assignCourse(String courseCode, String professorId);
    public void addCourse(Course course, List<Course> courseList);
    public void deleteCourse(String courseCode, List<Course> courseList);
    public void viewCourses();

    public List<Student> viewPendingStudentApprovals();
    public void approveStudent(int studentId, List<Student> studentList);
    public void addProfessor(Professor professor);
    public List<Professor> viewProfessors();

    public String getProfessorIdFromCourseID();

    public void setCourseAvailability(String courseId) throws SQLException;
}
