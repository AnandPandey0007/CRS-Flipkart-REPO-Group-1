/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.Grade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.utils.DBUtils;


/**
 * 
 * @author JEDI-03
 * Class to implement Registration Dao Operations
 * This class communicates with the database.
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface{
    
	
	private static volatile RegistrationDaoOperation instance=null;
	private static Logger logger = Logger.getLogger(RegistrationDaoOperation.class);
	private PreparedStatement stmt = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationDaoOperation() 
	{}
	
	/**
	 * Method to make RegistrationDaoOperation Singleton
	 * @return
	 */
	public static RegistrationDaoOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(RegistrationDaoOperation.class){
				instance=new RegistrationDaoOperation();
			}
		}
		return instance;
	}
	
	

	/**
	 * Method to add course in database
	 * @param courseCode
	 * @param studentId
	 * @return boolean indicating if the course is added successfully
	 * @throws SQLException 
	 */
	@Override
	public boolean addCourse(String courseCode, int studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		

		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
			stmt.setInt(1, studentId);
			stmt.setString(2, courseCode);

			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			logger.info(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		return false;
		
	}
	
	
	/**
	 * Number of registered courses for a student
	 * @param studentId
	 * @return Number of registered courses for a student
	 * @throws SQLException 
	 */
	@Override
	public int numOfRegisteredCourses(int studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		int count = 0;
		try {

			stmt = conn.prepareStatement(SQLQueriesConstants.NUMBER_OF_REGISTERED_COURSES);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count++;
			}
			return count;

		}
		catch (SQLException se) 
		{

			logger.error(se.getMessage());

		} 
		catch (Exception e)
		{

			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return count;
	}


	/**
	 * Check if seat is available for that particular course
	 * @param courseCode
	 * @return status of seat availablity
	 * @throws SQLException 
	 */
	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {

		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.GET_SEATS);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return (rs.getInt("seats") > 0);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return true;
		

	}
	


	/**
	 * Method checks if the student is registered for that course
	 * @param courseCode
	 * @param studentId
	 * @return Students registration status
	 * @throws SQLException 
	 */
	@Override
	public boolean isRegistered(String courseCode, int studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		boolean check = false;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.IS_REGISTERED);
			stmt.setString(1, courseCode);
			stmt.setInt(2, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				check = true;
			}
		}
		catch(Exception e)
		{
			logger.info(e.getClass());
			logger.info(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return check;
		
	}


	/**
	 * Drop Course selected by student
	 * @param courseCode : code for selected course
	 * @param studentId
	 * @return status of drop course operation
	 * @throws CourseNotFoundException 
	 */
	@Override
	public boolean dropCourse(String courseCode, int studentId) throws SQLException {
	
		Connection conn = DBUtils.getConnection();
		
		
			try
			{
				stmt = conn.prepareStatement(SQLQueriesConstants.DROP_COURSE_QUERY);
				stmt.setString(1, courseCode);
				stmt.setInt(2, studentId);
				stmt.execute();
				
				stmt = conn.prepareStatement(SQLQueriesConstants.INCREMENT_SEAT_QUERY);
				stmt.setString(1, courseCode);
				stmt.execute();
				
				stmt.close();
				
				return true;
			}
			catch(Exception e)
			{
				logger.info("Exception found" + e.getMessage());
			}
			finally
			{
	
				stmt.close();
				conn.close();
			}
			
		
		return false;
		
	}
	
	/**
	 * Method to retrieve fee for the selected courses from the database and calculate total fee
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	
	@Override
	public double calculateFee(int studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		double fee = 0;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.CALCULATE_FEES);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getDouble(1);
		}
		catch(SQLException e)
		{
			logger.error(e.getErrorCode());
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return fee;
	}

	/**
	 * Method to view grade card of the student
	 * @param studentId
	 * @throws SQLException 
	 * @return Studen's grade card
	 */
	@Override
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException {
		
		Connection conn = DBUtils.getConnection();
		List<StudentGrade> grade_List = new ArrayList<>();
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_GRADE);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String grade = rs.getString("grade");
				StudentGrade obj = new StudentGrade(courseCode, courseName,grade);
				grade_List.add(obj);
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
			
		}
		
		return grade_List;
	}

	/**
	 * Method to get the list of courses available from course catalog 
	 * @param studentId
	 * @return list of courses
	 * @throws SQLException
	 */
	@Override
	public List<Course> viewCourses(int studentId) throws SQLException {
		
		List<Course> availableCourseList = new ArrayList<>();
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_AVAILABLE_COURSES);
			stmt.setInt(1, studentId);
			stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats")));

			}
			

		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		} 
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return availableCourseList;
		
	}

	/**
	 * Method to get the list of courses registered by the student
	 * @param studentId
	 * @return list of courses registered by student
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {

		Connection conn = DBUtils.getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);
			stmt.setInt(1, studentId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats")));

			}
		} 
		catch (SQLException e) 
		{
			logger.info(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return registeredCourseList;
	}

	/**
	 * Method to retrieve Student's registration status
	 * @param studentId
	 * @return Student's registration status
	 * @throws SQLException
	 */
	@Override
	public boolean getRegistrationStatus(int studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.GET_REGISTRATION_STATUS);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);

		} 
		catch (SQLException e) 
		{
			logger.info(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

		return status;
	}
	/**
	 * Method to set Student's registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	public void setRegistrationStatus(int studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstants.SET_REGISTRATION_STATUS);
			stmt.setInt(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			logger.info(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}


}
