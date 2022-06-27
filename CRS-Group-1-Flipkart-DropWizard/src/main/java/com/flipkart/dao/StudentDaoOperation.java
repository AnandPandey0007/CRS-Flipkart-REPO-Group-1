/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 
 * Class to implement Student Dao Operations
 *
 */
public class StudentDaoOperation implements StudentDaoInterface {
	
	private static volatile StudentDaoOperation instance=null;
	/**
	 * Default Constructor
	 */
	private StudentDaoOperation() {
		
	}
	
	/**
	 * Method to make StudentDaoOperation Singleton
	 * @return
	 */
	public static StudentDaoOperation getInstance() {
		if(instance==null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentDaoOperation.class){
				instance=new StudentDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * Method to add student to database
	 *
	 * @param student: student object containing all the fields
	 * @return true if student is added, else false
	 * @throws StudentNotRegisteredException
	 */
	@Override
	public String addStudent(Student student) throws StudentNotRegisteredException{
		Connection connection=DBUtils.getConnection();
		try {
			//open db connection
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setBoolean(3, Boolean.FALSE);
			preparedStatement.executeUpdate();
			} catch(Exception ex) {
			throw new StudentNotRegisteredException(student.getStudentName());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"SQL error");
				e.printStackTrace();
			}
		}
		return student.getUserId();
	}
	
	/**
	 * Method to retrieve Student Id from User Id
	 * @param userId
	 * @return Student Id
	 */
	@Override
	public String getStudentId(String userId) {
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_ID);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				return rs.getString("studentId");
			}
				
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Method to check if Student is approved
	 * @param studentId
	 * @return boolean indicating if student is approved
	 */
	@Override
	public boolean isApproved(String studentId) {
		try {
			Connection connection=DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return rs.getBoolean("isApproved");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
