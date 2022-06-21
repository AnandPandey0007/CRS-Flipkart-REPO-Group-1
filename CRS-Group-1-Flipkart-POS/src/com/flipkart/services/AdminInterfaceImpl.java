package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;

import java.util.*;

HashMap<String,Course> coursemap = new HashMap();

HashMap<String,Professor> professormap = new HashMap();

public class AdminInterfaceImpl implements AdminInterface{

    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) 
    {
        if(coursemap.containsKey(courseCode))
        {
            courseList.remove(coursemap.get(courseCode));
            System.out.println("Course deleted.");
        }
        else
        {
            System.out.println("Course do not exist.");
        }
    }

    @Override
    public void addCourse(Course course, List<Course> courseList)
    {
        if(coursemap.containsValue(course))
        {
            System.out.println("Course already exist.");
        }
        else
        {
            courseList.put(Course);
            coursemap.put(course.courseCode,Course);
            System.out.println("Course added.");
        }
    }

    @Override
    public void approveStudent(int studentId, List<Student> studentList)
    {


    }

    @Override
    public void addProfessor(Professor professor)
    {
        if(professormap.containsValue(professor))
        {
            System.out.println("Professor already exist.");
        }
        else
        {
            professormap.put(professor.professorId,professor);
            System.out.println("Professor added.");
        }

    }

    @Override
    public void assignCourse(String courseCode, String professorId)
    {
        if(coursemap.containsKey(courseCode))
        {
            (course.get(courseCode)).instructorId = professorId;
            System.out.println("Course assigned.");
        }
        else
        {
            System.out.println("Course with this course code do not exist.");
        }
    }

    @Override
    public List<Course> viewCourses(int catalogId) {

        List<Course> courseList = new ArrayList(coursemap.values());
        return courseList;
    }

    @Override
    public List<Student> viewPendingStudentApprovals() {
        return null;
    }

    @Override
    public List<Professor> viewProfessors()
    {
        List<Professor> professorList = new ArrayList(professormap.values());
        return professorList;
    }
}
