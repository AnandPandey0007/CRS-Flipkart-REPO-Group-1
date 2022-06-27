package com.flipkart.validator;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.util.List;


/**
 * 
 * 
 * Class for Professor Validator
 * 
 */
public class ProfessorValidator {
	
	/**
	 * Method to check if Student exist in the database
	 * @param students: list of students in the database
	 * @param studentId: current student
	 * @return true, if student is valid. else, false.
	 */
	public static boolean isValidStudent(List<EnrolledStudent> students,String studentId)
	{
		boolean result=false;
		//check if student exist in ihe students list
		for(int i=0;i<students.size();i++)
		{
			//role.equalsIgnoreCase("ADMIN")
			if(students.get(i).getStudentId().equals(studentId))
				result=true;
				
		}
		return result;
	}
	
	/**
	 * Method to check if course exist in the database
	 * @param assignedCourses: list of courses assigned to the professor
	 * @param courseID: course id for which grade needs to be added
	 * @return true, if course is valid and taught by professor, else false.
	 */
	public static boolean isValidCourse(List<Course> assignedCourses,String courseID)
	{
		//check if course is valid
		boolean result=false;
		for(int i=0;i<assignedCourses.size();i++)
		{
			if(assignedCourses.get(i).getCourseCode().equalsIgnoreCase(courseID))
				result= true;
		}
		return result;
	}

}
