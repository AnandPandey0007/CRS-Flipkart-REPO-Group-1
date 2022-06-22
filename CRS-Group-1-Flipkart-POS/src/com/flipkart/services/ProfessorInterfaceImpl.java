package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterfaceImpl;

import java.util.ArrayList;
import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {

    @Override
    public String getProfessorById(String profId) {
        return null;
    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String courseId, String profId) {
        List<EnrolledStudent> studentList=null;
        try{
            ProfessorDaoInterfaceImpl p = new ProfessorDaoInterfaceImpl();
            studentList= p.getEnrolledStudents(courseId, profId);
        }
        catch(Exception e) {
            System.out.println("Error finding enrolled students !!!");
        }
        return studentList;
    }

    @Override
    public boolean addGrade(String studentId, String courseId, String grade) {
        List<EnrolledStudent> studentList=null;
        try{
            ProfessorDaoInterfaceImpl p = new ProfessorDaoInterfaceImpl();
            p.addGrade(studentId, courseId, grade);
        }
        catch(Exception e) {
            System.out.println("Error adding grade !!!");
        }
        return true;

    }

    @Override
    public List<Course> getCourses(String profId) {
        // TODO Auto-generated method stub
        try{
            ProfessorDaoInterfaceImpl p = new ProfessorDaoInterfaceImpl();
            return p.getCourses(profId);
        }
        catch(Exception e) {
            //throw new CourseNotFoundException(courseId);
        }
        return null;
    }
}
