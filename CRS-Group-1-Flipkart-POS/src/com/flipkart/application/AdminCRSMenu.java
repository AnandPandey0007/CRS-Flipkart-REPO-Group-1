package com.flipkart.application;

import java.util.Scanner;

public class AdminCRSMenu {
    Scanner scanner=new Scanner(System.in);
    //Display the options available for the Admin
    public void showAdminMenu(String adminId) {
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
