package com.flipkart.dao;

import com.flipkart.beans.EnrolledStudent;

import java.util.List;

public class ProfessorDAOOperation implements ProfessorDAOInterface{

    /**
     * @param profId
     * @return
     */
    @Override
    public String getProfessorById(String profId) {
        return null;
    }

    /**
     * @param profId
     * @return
     */
    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        return null;
    }

    /**
     * @param studentId
     * @param courseCode
     * @param grade
     * @return
     */
    @Override
    public boolean addGrade(int studentId, String courseCode, String grade) {
        return false;
    }
}
