package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisteredCourseOperation implements RegisteredCourseInterface {
    /**
     * @param studentId
     * @param professorId
     * @param courseID
     */
    private PreparedStatement stmt = null;
    @Override
    public void addCourse(String studentId, String professorId, String courseId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            stmt.setString(1, courseId);
            stmt.setString(2, professorId);
            stmt.setString(3, studentId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
