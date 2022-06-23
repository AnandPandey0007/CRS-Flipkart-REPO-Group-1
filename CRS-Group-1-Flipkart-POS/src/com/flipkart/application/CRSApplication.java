package com.flipkart.application;

import com.flipkart.beans.Professor;
import com.flipkart.constant.Role;
import com.flipkart.services.UserInterfaceImpl;
import com.flipkart.utils.DBUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class CRSApplication {
    static Scanner scanner=new Scanner(System.in);
    static UserInterfaceImpl userInterface=new UserInterfaceImpl();
    static String userId;

    public static void main(String args[]) throws Exception {
        System.out.println("CRS-Application is running");
        DBUtils.setupConnection();
        showMainMenu();
    }

    public static void showMainMenu() throws Exception {
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
    public static void login() throws IOException, Exception {
        System.out.println("Welcome to login page...");
        System.out.println("Enter Student/Professor/Admin");
        String role=scanner.next();
        if(role.equals("student")) {
            if (checkCredentials(Role.stringToRole("student"))) {
                StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                studentCRSMenu.showStudentMenu(userId);
            } else {
                System.out.println("Authentication failed");
            }
        } else if(role.equals("professor")) {
            if (checkCredentials(Role.stringToRole("professor"))) {
                ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                professorCRSMenu.showProfessorMenu(userId);
            } else {
                System.out.println("Authentication failed");
            }
        } else if(role.equals("admin")) {
            if (checkCredentials(Role.stringToRole("admin"))) {
                AdminCRSMenu adminCRSMenuCRSMenu = new AdminCRSMenu();
                adminCRSMenuCRSMenu.showAdminMenu(userId);
            } else {
                System.out.println("Authentication failed");
            }
        } else {
            System.out.println("Enter a valid choice");
        }
    }

    public static boolean checkCredentials(Role role) throws Exception {
        System.out.println("Enter your studentId: ");
        userId=scanner.next();
        System.out.println("Enter your password");
        String password=scanner.next();
        return userInterface.loginUser(userId, password);
    }

    public static void registerStudent(){
        System.out.println("Registering student....");
    }

    public static void updatePassword(){
        System.out.println("Updating password.....");
    }

}