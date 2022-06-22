package com.flipkart.application;

import com.flipkart.beans.EnrolledStudent;
import com.flipkart.services.ProfessorInterface;
import com.flipkart.services.ProfessorInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorCRSMenu {
        Scanner scanner=new Scanner(System.in);
        //Display the options available for the Professor
        public void showProfessorMenu(String profId) {
            System.out.println("*****************************");
            System.out.println("**********Professor Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Enrolled Students");
            System.out.println("2. Add grade");
            System.out.println("3. Logout");
            System.out.println("*****************************");
            System.out.println("Enter user input: ");
            int choice= scanner.nextInt();
            switch(choice){
                case 1: viewEnrolledStudents(profId);
                        break;
                case 2: addGrade(profId);
                        break;
                case 3: System.out.println("Logged out successfully");
                        break;
                default:
                    System.out.println("Invalid choice");
            }
     }

     public void viewEnrolledStudents(String profId)
     {
         List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
         ProfessorInterfaceImpl professorInterface= new ProfessorInterfaceImpl();
         enrolledStudents = professorInterface.viewEnrolledStudents(profId);
         System.out.println("List of enrolled students : ");
         for(EnrolledStudent stli: enrolledStudents)
         {
             System.out.println("Course code : " + stli.getCourseCode() + ", Student Id : " + stli.getStudentId());
         }
     }

     public void addGrade(String profId) {
         Scanner sc = new Scanner(System.in);
         int studentId;
         String courseCode, grade;
         List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
         ProfessorInterfaceImpl professorInterface= new ProfessorInterfaceImpl();
         enrolledStudents = professorInterface.viewEnrolledStudents(profId);

         System.out.println("Assign grade to student : ");
         System.out.println("Enter Student Id : ");
         studentId = sc.nextInt();
         System.out.println("Enter Course Code : ");
         courseCode = sc.next();
         System.out.println("Enter Grade : ");
         grade = sc.next();
         professorInterface.addGrade(studentId,courseCode,grade);
     }
}
