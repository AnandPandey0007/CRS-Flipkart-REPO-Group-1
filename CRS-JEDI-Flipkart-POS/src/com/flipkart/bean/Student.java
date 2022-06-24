/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

/**
 * 
 *
 * Student Class
 * 
 */
public class Student extends User {


	private String studentId;



	private String studentName;
	private boolean isApproved;
	
	
	/**
	 * Parameterized Constructor
	 * @param userId: email address of the user
	 * @param password: user password
	 * @param name: user full name
	 * @param role: role among student, professor, admin
	 * @param isApproved: check if student is approved by the admin or not
	 */

	public Student(String userId, String password, String name, Role role, boolean isApproved) {
		super(userId, password,  role);
		this.studentId = userId;
		this.studentName=name;
		this.isApproved = isApproved;
	}
	
	/**
	 * Default Constructor
	 */
	public Student() {
		
	}
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * Method to set approval status of student
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
