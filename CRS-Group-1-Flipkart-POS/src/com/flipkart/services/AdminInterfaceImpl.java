package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;

import java.util.*;
public class AdminInterfaceImpl implements AdminInterface{
    HashMap<String,Course> courseMap = new HashMap();
    HashMap<String,Professor> professorMap = new HashMap();

    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {
        if(courseMap.containsKey(courseCode)) {
            courseList.remove(courseMap.get(courseCode));
            System.out.println("Course deleted.");
        }
        else {
            System.out.println("Course do not exist.");
        }
    }

    @Override
    public void addCourse(Course course, List<Course> courseList) {
        if(courseMap.containsValue(course)) {
            System.out.println("Course already exist.");
        }
        else {
            courseList.add(course);
            courseMap.put(course.getCourseCode(),course);
            System.out.println("Course added.");
        }
    }

    @Override
    public void approveStudent(int studentId, List<Student> studentList) {


    }

    @Override
    public void addProfessor(Professor professor) {
        if(professorMap.containsValue(professor)) {
            System.out.println("Professor already exist.");
        }
        else {
            professorMap.put(professor.getProfessorId(),professor);
            System.out.println("Professor added.");
        }

    }

    @Override
    public void assignCourse(String courseCode, String professorId)
    {
        if(courseMap.containsKey(courseCode)) {
            courseMap.get(courseCode).setInstructorId(professorId);
            System.out.println("Course assigned.");
        }
        else {
            System.out.println("Course with this course code do not exist.");
        }
    }

    @Override
    public List<Course> viewCourses(int catalogId) {
        List<Course> courseList = new ArrayList(courseMap.values());
        return courseList;
    }

    @Override
    public List<Student> viewPendingStudentApprovals() {
        return null;
    }

    @Override
    public List<Professor> viewProfessors() {
        List<Professor> professorList = new ArrayList(professorMap.values());
        return professorList;
    }
}
