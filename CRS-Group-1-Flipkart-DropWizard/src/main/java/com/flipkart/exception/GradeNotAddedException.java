package com.flipkart.exception;

/**
 * Exception to check if student has been alloted grade by professor
 * 
 *
 */
public class GradeNotAddedException extends Exception{

	private String studentId;
	 
	/**
	 * Constructor
	 * @param studentId
	 */
	 public GradeNotAddedException(String studentId)
	 {
		 this.studentId=studentId;
	 }
	 
	 /**
	  * Getter function for studentId
	  * @return
	  */
	 public String getStudentId()
	 {
		 return studentId;
	 }
	 
	 
}
