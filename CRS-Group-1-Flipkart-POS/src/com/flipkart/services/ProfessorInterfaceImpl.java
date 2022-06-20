package com.flipkart.services;

import com.flipkart.beans.EnrolledStudent;

import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {

    @Override
    public String getProfessorById(String profId) {
        return null;
    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        return null;
    }

    @Override
    public boolean addGrade(int studentId, String courseCode, String grade) {
        return false;
    }
}
