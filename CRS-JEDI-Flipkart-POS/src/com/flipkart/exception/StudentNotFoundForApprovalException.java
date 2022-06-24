/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception thrown when student is not found for approval
 * 
 *
 */
public class StudentNotFoundForApprovalException extends Exception {
	private String studentId;
	
	public StudentNotFoundForApprovalException(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * Getter function for studentId
	 * @return
	 */
	public String getStudentId() {
		return this.studentId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "studentId: " + studentId + " not found for approval!" ;
	}
}
