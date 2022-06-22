package com.flipkart.services;
import com.flipkart.beans.Course;
import com.flipkart.beans.EnrolledStudent;
import java.util.*;

public interface ProfessorInterface {
    public String getProfessorById(String profId);
    public List<EnrolledStudent> viewEnrolledStudents(String courseId, String profId);
    public boolean addGrade(String studentId, String courseId, String grade);
    public List<Course> getCourses(String profId);
}
