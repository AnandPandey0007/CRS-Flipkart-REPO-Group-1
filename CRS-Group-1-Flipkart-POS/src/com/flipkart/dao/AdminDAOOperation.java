package com.flipkart.dao;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdminDAOOperation implements AdminDAOInterface{
    private PreparedStatement stmt = null;
    /**
     * @param courseCode
     * @param professorId
     */
    @Override
    public void assignCourse(String courseCode, String professorId) {

    }

    /**
     * @param course
     * @param courseList
     */
    @Override
    public void addCourse(Course course, List<Course> courseList) {

    }

    /**
     * @param courseCode
     * @param courseList
     */
    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {

    }

    /**
     *
     */
    @Override
    public void viewCourses() {

    }

    /**
     * @return
     */
    @Override
    public List<Student> viewPendingStudentApprovals() {
        return null;
    }

    /**
     * @param studentId
     * @param studentList
     */
    @Override
    public void approveStudent(int studentId, List<Student> studentList) {

    }

    /**
     * @param professor
     */
    @Override
    public void addProfessor(Professor professor) {

    }

    /**
     * @return
     */
    @Override
    public List<Professor> viewProfessors() {
        return null;
    }

    public String getProfessorIdFromCourseID(){
        return null;
    }
    public void setCourseAvailability(String courseId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
            stmt.setString(1, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
