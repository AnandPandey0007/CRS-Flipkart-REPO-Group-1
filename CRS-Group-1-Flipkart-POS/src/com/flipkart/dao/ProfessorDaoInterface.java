package com.flipkart.dao;

import java.util.*;
import com.flipkart.beans.Course;
import com.flipkart.beans.EnrolledStudent;

public interface ProfessorDaoInterface {

    public List<Course> getCourses(String profId);
    public List<EnrolledStudent> getEnrolledStudents(String courseId, String profId);
    public String getProfessorById(String profId);
    public Boolean addGrade(String studentId,String courseCode,String grade);
}