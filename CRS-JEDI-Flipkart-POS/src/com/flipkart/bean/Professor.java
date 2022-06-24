/**
 * 
 */
package com.flipkart.bean;
import java.util.Date;
/**
 *
 *
 */
public class Professor extends User{


	private String name;
	private String department;
	private String designation;
	
	/**
	 * Constructor
	 */
	public Professor() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * 
	 * @return: get the designation
	 */
	public String getDesignation() {
		return designation;
	}
	
	/**
	 * 
	 * @param designation: get the designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/**
	 * 
	 * @return departmet
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * 
	 * @param department: department for the professor
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
