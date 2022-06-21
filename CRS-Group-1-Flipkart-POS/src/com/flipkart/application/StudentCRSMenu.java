package com.flipkart.application;
import java.util.Scanner;
public class StudentCRSMenu {
    Scanner scanner=new Scanner(System.in);
    //display options available for the student
    public void showStudentMenu(String studentId){
        System.out.println("Enter user input: ");
        int choice= scanner.nextInt();
        switch(choice){
            case 1: courseRegistration();
                break;
            case 2: addCourse();
                break;
            case 3: dropCourse();
                break;
            case 4: viewCourse();
                break;
            case 5: viewRegisteredCourses();
                break;
            case 6: viewGradeCard();
                break;
            case 7: makePayment();
                break;
            case 8: logout();
                break;
        }
    }
    public void showChoices(){
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
    }

    public void courseRegistration(){
        System.out.println("Show Course Catalog");
    }

    public void addCourse(){
        System.out.println("Adding Course");
    }
    public void dropCourse(){
        System.out.println("Deleting course");
    }
    public void viewCourse(){
        System.out.println("Approve student");
    }
    public void viewRegisteredCourses(){
        System.out.println("View Pending Student Approval");
    }
    public void viewGradeCard(){
        System.out.println("Adding Professor");
    }
    public void makePayment(){
        System.out.println("Assign Courses");
    }

    public void logout(){
        System.out.println("Logging you out...");
    }

}