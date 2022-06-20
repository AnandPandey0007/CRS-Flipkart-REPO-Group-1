package com.flipkart.services;
import com.flipkart.beans.EnrolledStudent;
import java.util.*;

public interface ProfessorInterface {
    public String getProfessorById(String profId);
    public List<EnrolledStudent> viewEnrolledStudents(String profId);
    public boolean addGrade(int studentId,String courseCode,String grade);
}
