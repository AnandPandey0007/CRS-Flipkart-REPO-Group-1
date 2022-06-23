package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 *
 * Interface for Registration Operation
 * 
 */
public interface RegistrationInterface {
	
	/**
	 * Method for a student to add a course
	 * @param courseId
	 * @param studentId
	 * @param courseList
	 * @return boolean to indicate if the course is added successfully
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException
	 * @throws CourseLimitExceedException
	 * @throws SQLException
	 */
	public boolean addCourse(String courseId, int studentId, List<Course> courseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException ;
	
	/**
	 *  Method for a student to drop a selected Course
	 * @param courseId
	 * @param studentId
	 * @param registeredCourseList 
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException
	 * @throws SQLException 
	 */
	public boolean dropCourse(String courseId, int studentId, List<Course> registeredCourseList) throws CourseNotFoundException, SQLException;
	
	/**
	 *  Method for viewing the list of available courses
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	public List<Course> viewCourses(int studentId) throws SQLException;
	
	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;
	
	/**
	 * Method for students to view grade card
	 * @param studentId
	 * @return List of Grades of Student
	 * @throws SQLException 
	 */
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;
	
	/** Method to calculate Fee for selected courses
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	public double calculateFee(int studentId) throws SQLException;

	/**
	 *  Method to check registration status for a student
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	public boolean getRegistrationStatus(int studentId) throws SQLException;
	
	/**
	 *  Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	public void setRegistrationStatus(int studentId) throws SQLException;
	
}
