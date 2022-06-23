/**
 * 
 */
package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/**
 * 
 * @author JEDI-03
 * Class for Admin Validator 
 * 
 */
public class AdminValidator {
	
	/**
	 * Method to validate if newCourse is not already present in catalog
	 * @param newCourse
	 * @param courseList
	 * @return if newCourse is not already present in catalog
	 */
	public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
		for(Course course : courseList) {
			if(newCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}
	
	/**
	 * Method to validate if dropCourse is already present in catalog
	 * @param dropCourseCode
	 * @param courseList
	 * @return if dropCourse is already present in catalog
	 */
	public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
		for(Course course : courseList) {
			if(dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Method to validate if studentId is still unapproved
	 * @param studentId
	 * @param studentList
	 * @return if studentId is still unapproved
	 */
	public static boolean isValidUnapprovedStudent(int studentId, List<Student> studentList) {
		for(Student student : studentList) {
			if(studentId == student.getStudentId()) {
				return true;
			}
		}
		return false;
	}
}