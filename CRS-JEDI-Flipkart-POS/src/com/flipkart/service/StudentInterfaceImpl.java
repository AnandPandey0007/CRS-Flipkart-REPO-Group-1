package com.flipkart.service;


import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 *
 * Implementations of Student Operations
 *
 */
public class StudentInterfaceImpl implements StudentInterface {
	
	private static volatile StudentInterfaceImpl instance=null;
	StudentDaoInterface studentDaoInterface=StudentDaoOperation.getInstance();

	private StudentInterfaceImpl()
	{
		
	}
	/**
	 * Method to make StudentInterfaceImpl Singleton
	 * @return
	 */
	public static StudentInterfaceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentInterfaceImpl.class){
				instance=new StudentInterfaceImpl();
			}
		}
		return instance;
	}
	
	/**
	 * Method to register a student, although student can't login until it's approved by admin
	 * @param name
	 * @param userID
	 * @param password
	 * @param gender
	 * @param batch
	 * @param branch
	 * @param address
	 * @param country
	 * @return Student ID
	 * @throws StudentNotRegisteredException
	 */
	@Override
	public int register(String name,String userId,String password,Gender gender,int batch,String branch,String address,String country) throws StudentNotRegisteredException{
		int studentId;
		try
		{
			//call the DAO class, and add the student record to the DB
			Student newStudent=new Student(userId,name,Role.STUDENT,password,gender,address,country,branch,0,batch,false);
			studentId=studentDaoInterface.addStudent(newStudent);
			
		}
		catch(StudentNotRegisteredException ex)
		{
			throw ex;
		}
		return studentId;
	}
	
	/**
	 * Method to get Student ID from User ID
	 * @param userId
	 * @return Student ID
	 */
	@Override
	public int getStudentId(String userId) {
		return studentDaoInterface.getStudentId(userId);
	
	}
	
	/**
     * Method to check if student is approved by Admin or not
     * @param studentId
     * @return boolean indicating if student is approved
     */
	@Override
	public boolean isApproved(int studentId) {
		return studentDaoInterface.isApproved(studentId);
	}


}
