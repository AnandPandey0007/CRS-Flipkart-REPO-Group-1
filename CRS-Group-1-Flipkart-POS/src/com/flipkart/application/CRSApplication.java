package com.flipkart.application;

import com.flipkart.beans.Professor;

import java.util.Scanner;

public class CRSApplication {

    public static void main(String args[]){
        System.out.println("CRS-Application is running");
        showMainMenu();
    }

    public static void showMainMenu(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("----------Welcome to Course Management System---------");
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Update password");
        System.out.println("4. Exit");
        System.out.println("Enter user input");
        int choice= scanner.nextInt();
        switch(choice){
            case 1: login();
            case 2:
            case 3:
            case 4:
        }
    }

    public static void login(){
    }
}
