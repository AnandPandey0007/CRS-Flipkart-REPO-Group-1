package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

/**
 * 
 *
 * Class to implement Professor Dao Operations
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static volatile ProfessorDaoOperation instance=null;
	
	
	/**
	 * Default Constructor
	 */
	private ProfessorDaoOperation()
	{
		
	}
	
	/**
	 * Method to make ProfessorDaoOperation Singleton
	 *
	 */
	public static ProfessorDaoOperation getInstance() {
		if(instance==null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(ProfessorDaoOperation.class){
				instance=new ProfessorDaoOperation();
			}
		}
		return instance;
	}
	
	
	/**
	 * Method to get Courses by Professor Id using SQL Commands
	 * @param  profId id of the professor
	 * @return get the courses offered by the professor.
	 */
	@Override
	public List<Course> getCoursesByProfessor(String profId) {
		Connection connection=DBUtils.getConnection();
		List<Course> courseList=new ArrayList<Course>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);
			
			statement.setString(1, profId);
			
			ResultSet results=statement.executeQuery();
			while(results.next()) {
				courseList.add(new Course(results.getString("courseCode"),results.getString("courseName"),results.getString("professorId"),results.getInt("seats")));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return courseList;
		
	}

	/**
	 * Method to view list of enrolled Students using SQL Commands
	 * @param profId professor id
	 * @return return the enrolled students for the corresponding professor and course code.
	 */
	@Override
	public List<EnrolledStudent> getEnrolledStudents(String profId) {
		Connection connection=DBUtils.getConnection();
		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
			statement.setString(1, profId);
			
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				//public EnrolledStudent(String courseCode, String courseName, int studentId) 
				enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),results.getString("courseName"),results.getString("studentId")));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return enrolledStudents;
	}
	
	/**
	 * Method to Grade a student using SQL Commands
	 * @param courseCode course code for the corresponding
	 * @return returns the status after adding the grade
	 */
	public Boolean addGrade(String studentId,String courseCode,String grade) {
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);
			
			statement.setString(1, grade);
			statement.setString(2, courseCode);
			statement.setString(3, studentId);
			
			int row = statement.executeUpdate();
			
			if(row==1)
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	

	/**
	 * Method to Get professor name by id
	 * @param profId
	 * @return Professor Id in string
	 */
	@Override
	public String getProfessorById(String profId) {
		String prof_Name = null;
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROF_NAME);
			
			statement.setString(1, profId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			
			prof_Name = rs.getString(1);
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return prof_Name;
	}
}
