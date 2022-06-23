package com.flipkart.service;


import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * Interface for Admin Operations
 * 
 */
public interface AdminInterface {
	

	/**
	 * Method to Drop Course from Course Catalog
	 * @param courseId
	 * @param courseList : List of Courses in the catalog
	 * @throws CourseNotFoundException 
	 * @throws CourseNotDeletedException 
	 */
	public void deleteCourse(String courseId, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;
	
	/**
	 * Method to add Course to Course Catalog
	 * @param course : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 */
	public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;
	
	/**
	 * Method to view Students remaining to be approved by Admin
	 * @return All Students with pending admissions
	 */
	public List<Student> viewPendingAdmissions();
	
	/**
	 * Method to approve a Student 
	 * @param studentId
	 * @param studentList
	 * @throws StudentNotFoundException 
	 * @throws StudentNotFoundForApprovalException 
	 */
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;
	
	/**
	 * Method to add Professor to DB
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 * @throws UserIdAlreadyInUseException 
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;	
	
	/**
	 * Method to allot a Course to a Professor
	 * @param courseId
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseId, String professorId) throws CourseNotFoundException, UserNotFoundException;
	
	/**
	 * Method to return list of courses in catalog
	 * @param catalogId
	 * @return List of courses in catalog
	 */
	public List<Course> viewCourses(int catalogId);
	
	/**
	 * Display professor in the institute
	 * @return List of the professors in the institute  
	 */
	public List<Professor> viewProfessors();
}
