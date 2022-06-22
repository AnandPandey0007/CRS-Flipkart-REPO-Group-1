package com.flipkart.application;

import com.flipkart.beans.Course;
import com.flipkart.beans.EnrolledStudent;
import com.flipkart.services.ProfessorInterface;
import com.flipkart.services.ProfessorInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorCRSMenu {
    public static void main(String[] args) {
        showProfessorMenu("1");
    }

    static Scanner scanner = new Scanner(System.in);

    //Display the options available for the Professor
    public static void showProfessorMenu(String profId) {
        System.out.println("*****************************");
        System.out.println("**********Professor Menu*********");
        System.out.println("*****************************");
        System.out.println("1. View Courses");
        System.out.println("2. View Enrolled Students in a course");
        System.out.println("3. Add grade");
        System.out.println("4. Logout");
        System.out.println("*****************************");
        System.out.println("Enter user input: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                viewCourses(profId);
                break;
            case 2:
                System.out.print("Enter the courseId to view enrolled students: ");
                String courseId = scanner.next();
                viewEnrolledStudents(courseId, profId);
                break;
            case 3:
                addGrade(profId);
                break;
            case 4:
                System.out.println("Logged out successfully");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void viewCourses(String professorId) {
        try {
            ProfessorInterfaceImpl professorImpl = new ProfessorInterfaceImpl();
            List<Course> courses = professorImpl.getCourses(professorId);
            System.out.println("size of returned list    "+courses.size());

            System.out.println("************COURSES*********************");

            for (Course course : courses) {
                String courseCode = "Course Code: " + course.getCourseCode();
                String courseName = "Course Name: " + course.getCourseName();

                System.out.printf("%-25s. %-20s\n", courseCode, courseName);

            }
            System.out.println("*******************************************\n");


        } catch (Exception e) {
            System.out.println("Error in viewing courses !!!");
        }
    }

    public static void viewEnrolledStudents(String courseId, String profId) {
        List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
        ProfessorInterfaceImpl professorInterface = new ProfessorInterfaceImpl();
        enrolledStudents = professorInterface.viewEnrolledStudents(courseId, profId);
        System.out.println("List of enrolled students : ");
        for (EnrolledStudent stli : enrolledStudents) {
            System.out.println("Course code : " + stli.getCourseCode() + ", Student Id : " + stli.getStudentId());
        }
    }

    public static void addGrade(String profId) {
        Scanner sc = new Scanner(System.in);
        String courseId, grade;
        List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
        ProfessorInterfaceImpl professorInterface = new ProfessorInterfaceImpl();
        //enrolledStudents = professorInterface.viewEnrolledStudents(courseId, profId);

        System.out.println("Assign grade to student : ");
        System.out.println("Enter Student Id : ");
        String studentId = sc.next();
        System.out.println("Enter Course Code : ");
        courseId = sc.next();
        System.out.println("Enter Grade : ");
        grade = sc.next();
        professorInterface.addGrade(studentId, courseId, grade);
    }
}
