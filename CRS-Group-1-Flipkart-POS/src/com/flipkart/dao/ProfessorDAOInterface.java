package com.flipkart.dao;

import com.flipkart.beans.EnrolledStudent;

import java.util.List;

public interface ProfessorDAOInterface {
    public String getProfessorById(String profId);

    public List<EnrolledStudent> viewEnrolledStudents(String profId);

    public boolean addGrade(int studentId, String courseCode, String grade);
}
