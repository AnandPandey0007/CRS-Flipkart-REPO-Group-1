package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * The Registration Operation provides the business logic for student registration.
 * 
 */
public class RegistrationInterfaceImpl implements RegistrationInterface {

	private static volatile RegistrationInterfaceImpl instance = null;

	private RegistrationInterfaceImpl() {
	}

	/**
	 * Method to make Registration Operation Singleton
	 * 
	 * @return
	 */
	public static RegistrationInterfaceImpl getInstance() {
		if (instance == null) {
			synchronized (RegistrationInterfaceImpl.class) {
				instance = new RegistrationInterfaceImpl();
			}
		}
		return instance;
	}

	RegistrationDaoOperation registrationDaoOperation = RegistrationDaoOperation.getInstance();

	/**
	 * Method to add Course selected by student 
	 * @param courseCode
	 * @param studentId
	 * @param availableCourseList
	 * @return boolean indicating if the course is added successfully
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException 
	 * @throws CourseLimitExceedException 
	 * @throws SQLException 
	 */
	@Override
	public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException
	{
		if (registrationDaoOperation.numOfRegisteredCourses(studentId) >= 6) {
			throw new CourseLimitExceedException(6);
		}
		else if (registrationDaoOperation.isRegistered(courseCode, studentId)) {
			return false;
		} else if (!registrationDaoOperation.seatAvailable(courseCode)) {
			throw new SeatNotAvailableException(courseCode);
		} 
		else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList)) {
			throw new CourseNotFoundException(courseCode);
		}
		return registrationDaoOperation.addCourse(courseCode, studentId);

	}

	/**
	 *  Method to drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList 
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException
	 * @throws SQLException 
	 */
	@Override
	public boolean dropCourse(String courseCode, String studentId,List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
		  if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList)) {
	        	throw new CourseNotFoundException(courseCode);
	        }
		
		return registrationDaoOperation.dropCourse(courseCode, studentId);
	}

	/** Method for Fee Calculation for selected courses
	 * Fee calculation for selected courses
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	@Override
	public double calculateFee(String studentId) throws SQLException {
		return registrationDaoOperation.calculateFee(studentId);
	}

	/**
	 * Method to view grade card for students
	 * @param studentId
	 * @return List of Student's Grades
	 * @throws SQLException 
	 */
	@Override
	public List<StudentGrade> viewGradeCard(String studentId) throws SQLException {
		return registrationDaoOperation.viewGradeCard(studentId);
	}

	/**
	 *  Method to view the list of available courses
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewCourses() throws SQLException {
		return registrationDaoOperation.viewCourses();
	}

	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		return registrationDaoOperation.viewRegisteredCourses(studentId);
	}
    
	/**
	 *  Method to check student registration status
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException {
		return registrationDaoOperation.getRegistrationStatus(studentId);
	}
	
	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		registrationDaoOperation.setRegistrationStatus(studentId);

	}

	public boolean checkCourse(String courseCode,String studentId,List<Course> availableCourseList) throws CourseLimitExceedException, CourseNotFoundException, SeatNotAvailableException, CourseAlreadyRegisteredException {

		try {
			int response = registrationDaoOperation.checkCourseAvailability(studentId, courseCode);
			if (response == 0){
				throw new CourseLimitExceedException(6);
			}
			else if (response == 1) {
				throw new CourseAlreadyRegisteredException(courseCode);
			}
			else if (!registrationDaoOperation.seatAvailable(courseCode)) {
				throw new SeatNotAvailableException(courseCode);
			}
			else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList)){
				throw new CourseNotFoundException(courseCode);
			}

			return true;

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		return false;

	}


}
