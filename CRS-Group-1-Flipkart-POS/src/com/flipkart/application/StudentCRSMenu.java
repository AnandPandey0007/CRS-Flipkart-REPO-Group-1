package com.flipkart.application;

import java.util.Scanner;

public class StudentCRSMenu {
    Scanner scanner=new Scanner(System.in);
    //display options available for the student
    public void showStudentMenu(String studentId){
        System.out.println("*****************************");
        System.out.println("**********Student Menu*********");
        System.out.println("*****************************");
        System.out.println("1. Course Registration");
        System.out.println("2. Add Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View Course");
        System.out.println("5. View Registered Courses");
        System.out.println("6. View grade card");
        System.out.println("7. Make Payment");
        System.out.println("8. Logout");
        System.out.println("*****************************");
        System.out.println("Enter user input: ");
        int choice= scanner.nextInt();
        switch(choice){
            case 1:
            case 2:
            case 3:
            case 4:
        }
    }

}