package com.flipkart.application;

import java.util.Scanner;

public class ProfessorCRSMenu {
        Scanner scanner=new Scanner(System.in);
        //Display the options available for the Professor
        public void showProfessorMenu(String profId) {
            System.out.println("*****************************");
            System.out.println("**********Professor Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Logout");
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
