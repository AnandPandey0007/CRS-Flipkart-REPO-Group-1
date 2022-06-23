package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.validator.AdminValidator;

/**
 *
 * Implementations of Admin Operations
 *
 */
public class AdminInterfaceImpl implements AdminInterface{
	private static volatile AdminInterfaceImpl instance = null;

	private AdminInterfaceImpl()
	{

	}

	/**
	 * Method to make AdminInterfaceImpln Singleton
	 */
	public static AdminInterfaceImpl getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminInterfaceImpl.class){
				instance = new AdminInterfaceImpl();
			}
		}
		return instance;
	}


	AdminDaoInterface adminDaoOperation =AdminDaoOperation.getInstance();

	/**
	 * Method to Delete Course from Course Catalog
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException
	 */
	@Override
	public void deleteCourse(String dropCourseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException {

		if(!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
			System.out.println("courseCode: " + dropCourseCode + " not present in catalog!");
			throw new CourseNotFoundException(dropCourseCode);
		}

		try {
			adminDaoOperation.deleteCourse(dropCourseCode);
		}
		catch(CourseNotFoundException | CourseNotDeletedException e) {
			throw e;
		}

	}

	/**
	 * Method to add Course to Course Catalog
	 * @param courseList : Courses available in catalog
	 * @throws CourseFoundException
	 */
	@Override
	public void addCourse(Course newCourse, List<Course> courseList) throws CourseFoundException {

		if(!AdminValidator.isValidNewCourse(newCourse, courseList)) {
			System.out.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
			throw new CourseFoundException(newCourse.getCourseCode());
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		}
		catch(CourseFoundException e) {
			throw e;
		}

	}

	/**
	 * Method to view Students yet to be approved by Admin
	 * @return List of Students with pending admissions
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	/**
	 * Method to approve a Student 
	 * @param studentId
	 * @param studentList
	 */
	@Override
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

		if(!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
			//logger.error("studentId: " + studentId + " is already approvet/not-present!");
			throw new StudentNotFoundForApprovalException(studentId);
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		}
		catch(StudentNotFoundForApprovalException e) {
			throw e;
		}

	}

	/**
	 * Method to add Professor to DB
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 */
	@Override
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

		try {
			adminDaoOperation.addProfessor(professor);
		}
		catch(ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			throw e;
		}

	}

	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 */
	@Override
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		}
		catch(CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}

	}

	/**
	 * Method to get list of courses in catalog
	 * @param catalogId
	 * @return List of courses in catalog
	 */
	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */
	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}
}