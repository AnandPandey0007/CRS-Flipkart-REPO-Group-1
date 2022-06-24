package com.flipkart.service;

import com.flipkart.constant.Gender;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * 
 * 
 * Interface for Student Operations
 *
 */
public interface StudentInterface {
	
	/**
	 * Method to register a student, although student can't login until it's approved by admin
	 * @param userId
	 * @param password
	 * @param name
	 * @return Student ID
	 * @throws StudentNotRegisteredException
	 */
	public String register(String userId,String password,String name) throws StudentNotRegisteredException;
	/**
	 * Method to get Student ID from User ID
	 * @param userId
	 * @return Student ID
	 */
	public String getStudentId(String userId);
	
	/**
     * Method to check if student is approved by Admin or not
     * @param studentId
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(String studentId);
}
