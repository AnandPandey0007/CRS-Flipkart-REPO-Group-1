package com.flipkart.application;

import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author anand.pandey4
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 * 
 */
public class CRSApplication {
	static boolean loggedin = false;
	StudentInterface studentInterface= StudentInterfaceImpl.getInstance();
	UserInterface userInterface = UserInterfaceImpl.getInstance();
	NotificationInterface notificationInterface= NotificationInterfaceImpl.getInstance();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CRSApplication crsApplication=new CRSApplication();
		int userInput;	
		//create the main menu
		createMainMenu();
		userInput=sc.nextInt();
		try
		{
			
		//until user do not exit the application
		while(userInput!=4) {
			switch(userInput) {
				case 1:
					//login
					crsApplication.loginUser();
					break;
				case 2:
					//student registration
					crsApplication.registerStudent();
					break;	
				case 3:
					crsApplication.updatePassword();
					break;
				default:
					System.out.println("Invalid Input");
			}
			createMainMenu();
			userInput=sc.nextInt();
		}
		}
		catch(Exception ex) {
			System.out.println("Error occured "+ex);
		}
		finally {
			sc.close();
		}
	}
	
	/**
	 * Method to Create Main Menu
	 */
	public static void createMainMenu() {
		System.out.println("----------Welcome to Course Management System---------");
		System.out.println("1. Login");
		System.out.println("2. Student Registration");
		System.out.println("3. Update password");
		System.out.println("4. Exit");
		System.out.println("Enter user input");
	}
	
	/**
	 * Method for Login functionality
	 */
	public void loginUser() {
		//Following can be the exceptions
		//invalid credential exception
		//user not found exception
		//user not approved exception
		//user not validated
		Scanner sc=new Scanner(System.in);

		String userId,password;
		try {
			System.out.println("-----------------Login------------------");
			System.out.println("User-Id:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			loggedin = userInterface.verifyCredentials(userId, password);
			//2 cases
			//true->role->student->approved
			if(loggedin) {
				 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				 LocalDateTime myDateObj = LocalDateTime.now();
				 String formattedDate = myDateObj.format(myFormatObj);
				//System.out.println("Welcome "+userId);
				 String role=userInterface.getRole(userId);
				 Role userRole=Role.stringToName(role);
				switch(userRole)
				{
				case ADMIN:
					System.out.println(formattedDate + " Login Successful");
					AdminCRSMenu adminMenu=new AdminCRSMenu();
					adminMenu.createMenu();
					break;
				case PROFESSOR:
					System.out.println(formattedDate + " Login Successful");
					ProfessorCRSMenu professorMenu=new ProfessorCRSMenu();
					professorMenu.createMenu(userId);
					break;
				case STUDENT:
					String studentId=userId;
					boolean isApproved=studentInterface.isApproved(studentId);
					if(isApproved) {
						System.out.println(formattedDate + " Login Successful");
						StudentCRSMenu studentMenu=new StudentCRSMenu();
						studentMenu.create_menu(studentId);
					} else {
						System.out.println("Failed to login, you have not been approved by the administration!");
						loggedin=false;
					}
					break;
				}
			}
			else {
				System.out.println("Invalid Credentials!");
			}
			
		}
		catch(UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}	
	}
	
	/**
	 * Method to help Student register themselves, pending admin approval
	 */
	public void registerStudent() {
		Scanner sc=new Scanner(System.in);
		String userId,name,password;
		try {
			//input all the student details
			System.out.println("---------------Student Registration-------------");
			System.out.println("Full-Name");
			name=sc.nextLine();
			System.out.println("UserId:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			String newStudentId=studentInterface.register(userId, password, name);
			notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null,0);
		}
		catch(StudentNotRegisteredException ex) {
			System.out.println("Something went wrong! "+ex.getStudentName() +" not registered. Please try again");
		}
	}
	
	/**
	 * Method to update password of User
	 */
	public void updatePassword() {
		Scanner sc=new Scanner(System.in);
		String userId,newPassword;
		try {
			System.out.println("------------------Update Password--------------------");
			System.out.println("Email");
			userId=sc.next();
			System.out.println("New Password:");
			newPassword=sc.next();
			boolean isUpdated=userInterface.updatePassword(userId, newPassword);
			if(isUpdated)
				System.out.println("Password updated successfully!");

			else
				System.out.println("Something went wrong, please try again!");
		}
		catch(Exception ex)
		{
			System.out.println("Error Occured "+ex.getMessage());
		}
	}
}
