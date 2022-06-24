
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

/**
 * 
 *
 * User Class
 *
 */
public abstract class User {
	private String userId;
	private Role role;
	private String password;
	

	/**
	 * Parameterized Constructor
	 * @param userId: email address of the user
	 * @param role: user's role
	 * @param password: user's password
	 */
	public User(String userId, String password, Role role) {
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	
	/**
	 * Default Constructor
	 */
	public User(){
		
	}
	
	/**
	 * Method to get User's Id
	 * @return User Id
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Method to set User's Id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Method to get User's Name
	 * @return User Name
	 */
	
	/**
	 * Method to get User's Role
	 * @return User Role
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * Method to set User's Role
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Method to get User's Password
	 * @return User Password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Method to set User's Password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
