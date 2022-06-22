package com.flipkart.services;

import com.flipkart.beans.Course;
import com.flipkart.beans.Professor;
import com.flipkart.beans.Student;

import java.util.*;
public class AdminInterfaceImpl implements AdminInterface{
    TreeMap<String,Course> courseMap = new TreeMap();
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
    public void viewCourses() {
        fillCourses();
       for(String key: courseMap.keySet()){
           System.out.print(courseMap.get(key).getCourseCode()+"  "+ courseMap.get(key).getCourseName());
           System.out.println();
       }
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

    public void fillCourses(){
        courseMap.put("CSE100", new Course("CSE100","DBMS", "PROF_100", 50));
        courseMap.put("CSE101", new Course("CSE101","OS", "PROF_101", 50));
        courseMap.put("CSE102", new Course("CSE102","SoftwareEngineering", "PROF_102", 50));
        courseMap.put("CSE103", new Course("CSE103","NumberTheory", "PROF_103", 50));
        courseMap.put("CSE104", new Course("CSE104","DataStructures", "PROF_104", 50));
        courseMap.put("CSE105", new Course("CSE105","GameTheory", "PROF_105", 50));
        courseMap.put("CSE106", new Course("CSE106","NetworkSecurity", "PROF_106", 50));
    }

    public Course getCourseFromId(String courseId){
        if(courseMap.containsKey(courseId)){
            return courseMap.get(courseId);
        } else {
            return null;
        }
    }
}
