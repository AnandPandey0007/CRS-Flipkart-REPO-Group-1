package com.flipkart.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdminCRSMenu {
    Scanner scanner=new Scanner(System.in);
    BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
    //Display the options available for the Admin

    public void showAdminMenu(String adminId) {
        showChoices();
        System.out.println("Enter user input: ");
        int choice= scanner.nextInt();
        switch(choice){
            case 1: showCourses();
                    break;
            case 2: addCourse();
                    break;
            case 3: deleteCourse();
                    break;
            case 4: approveStudent();
                    break;
            case 5: viewPendingStudentApproval();
                    break;
            case 6: addProfessor();
                    break;
            case 7: assignCourse();
                    break;
            case 8: logout();
                    break;
        }
    }

    public void showChoices(){
        System.out.println("*****************************");
        System.out.println("**********Admin Menu*********");
        System.out.println("*****************************");
        System.out.println("1. View course in catalog");
        System.out.println("2. Add Course to catalog");
        System.out.println("3. Delete Course from catalog");
        System.out.println("4. Approve Students");
        System.out.println("5. View Pending Student Approval");
        System.out.println("6. Add Professor");
        System.out.println("7. Assign Courses To Professor");
        System.out.println("8. Logout");
        System.out.println("*****************************");
    }

    public void showCourses(){
        System.out.println("Show Course Catalog");
    }

    public void addCourse(){
        System.out.println("Adding Course");
    }
    public void deleteCourse(){
        System.out.println("Deleting course");
    }
    public void approveStudent(){
        System.out.println("Approve student");
    }
    public void viewPendingStudentApproval(){
        System.out.println("View Pending Student Approval");
    }
    public void addProfessor(){
        System.out.println("Adding Professor");
    }
    public void assignCourse(){
        System.out.println("Assign Courses");
    }

    public void logout(){
        System.out.println("Logging you out...");
    }
}
