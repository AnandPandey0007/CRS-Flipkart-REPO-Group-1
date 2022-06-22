package com.flipkart.application;

import com.flipkart.beans.Professor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CRSApplication {
    static Scanner scanner=new Scanner(System.in);
    static HashMap<String, String> studentCredentials=new HashMap<>();
    static HashMap<String, String> professorCredentials=new HashMap<>();
    static HashMap<String, String> adminCredentials=new HashMap<>();

    static String userId;

    public static void main(String args[]) throws IOException {
        System.out.println("CRS-Application is running");
      //  hardCodeData();
      //  showMainMenu();
    }

    public static void showMainMenu() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("----------Welcome to Course Management System---------");
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Update password");
        System.out.println("4. Exit");
        System.out.println("Enter user input");
        int choice= scanner.nextInt();
        switch(choice){
            case 1:  login();
                     break;
            case 2:  registerStudent();
                     break;
            case 3:  updatePassword();
                     break;
            default: System.out.println("Thanks for using CRS-Application");
                     break;
        }
    }

    public static void login() throws IOException {
        System.out.println("Welcome to login page...");
        System.out.println("Enter S for Student");
        System.out.println("Enter P for Professor");
        System.out.println("Enter A for Admin");
        char role=scanner.next().charAt(0);
        switch (role){
            case 'S':
                if(checkCredentials(studentCredentials)) {
                    StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                    studentCRSMenu.showStudentMenu(userId);
                } else {
                    System.out.println("Authentication failed");
                }
                break;
            case 'P':
                if(checkCredentials(professorCredentials)){
                    ProfessorCRSMenu professorCRSMenu=new ProfessorCRSMenu();
                    professorCRSMenu.showProfessorMenu(userId);
                }else {
                    System.out.println("Authentication failed");
                }
                break;
            case 'A':
                if(checkCredentials(adminCredentials)){
                    AdminCRSMenu adminCRSMenuCRSMenu=new AdminCRSMenu();
                    adminCRSMenuCRSMenu.showAdminMenu(userId);
                }else {
                    System.out.println("Authentication failed");
                }
                break;
            default:
                System.out.println("Enter a valid choice");
                break;
        }
    }

    public static boolean checkCredentials(HashMap<String, String> user){
        System.out.println("Enter your studentId: ");
        userId=scanner.next();
        System.out.println("Enter your password");
        String password=scanner.next();
        return user.containsKey(userId) && user.get(userId).equals(password);
    }

    public static void registerStudent(){
        System.out.println("Registering student....");
    }

    public static void updatePassword(){
        System.out.println("Updating password.....");
    }

    public static void hardCodeData(){
        studentCredentials.put("AnandPandey", "Flipkart");
        adminCredentials.put("AnandPandey", "Flipkart");
        professorCredentials.put("AnandPandey", "Flipkart");
    }
}