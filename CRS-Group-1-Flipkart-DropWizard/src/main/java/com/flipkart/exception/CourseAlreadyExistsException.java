/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if course is already present in catalog
 * 
 *
 */
public class CourseAlreadyExistsException extends Exception{
	private String courseCode;
	
	/***
	 * Constructor
	 * @param courseCode
	 */
	public CourseAlreadyExistsException(String courseCode) {
		this.courseCode = courseCode;
	}
	

	/**
	 * Getter method
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " already present in catalog.";
	}
}
