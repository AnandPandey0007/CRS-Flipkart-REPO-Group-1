package com.flipkart.services;

import com.flipkart.beans.EnrolledStudent;

import java.util.ArrayList;
import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {

    @Override
    public String getProfessorById(String profId) {
        return null;
    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        EnrolledStudent st1= new EnrolledStudent("1","C1",1);
        enrolledStudents.add(st1);
        EnrolledStudent st2= new EnrolledStudent("1","C1",2);
        enrolledStudents.add(st2);
        EnrolledStudent st3= new EnrolledStudent("1","C1",2);
        enrolledStudents.add(st3);
        return enrolledStudents;
    }

    @Override
    public boolean addGrade(int studentId, String courseCode, String grade) {
            System.out.println("The grade of the studentId : " +studentId+" has been updated to "+grade);
            return true;
    }
}
