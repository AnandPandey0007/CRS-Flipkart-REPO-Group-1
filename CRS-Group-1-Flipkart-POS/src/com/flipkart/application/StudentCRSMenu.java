package com.flipkart.application;
import com.flipkart.services.AdminInterfaceImpl;
import com.flipkart.services.StudentInterfaceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentCRSMenu {
    BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
    AdminInterfaceImpl adminInterfaceImpl=new AdminInterfaceImpl();
    StudentInterfaceImpl studentInterfaceImpl=new StudentInterfaceImpl();
    //display options available for the student
    public void showStudentMenu(String studentId) throws IOException {
        int choice=9;
        showChoices();
        do {
        System.out.println("Enter user input: ");
        choice= Integer.parseInt(reader.readLine());
        switch(choice){
            case 1: courseRegistration(studentId);
                break;
            case 2: addCourse(studentId);
                break;
            case 3: dropCourse(studentId);
                break;
            case 4: viewCourse();
                break;
            case 5: viewRegisteredCourses(studentId);
                break;
            case 6: viewGradeCard();
                break;
            case 7: makePayment();
                break;
            case 8: logout();
                break;
        }}while(choice!=8);
    }
    public void showChoices(){
        System.out.println("******************************");
        System.out.println("**********Student Menu*********");
        System.out.println("******************************");
        System.out.println("1. Student Registration");
        System.out.println("2. Add Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View Available Courses");
        System.out.println("5. View Registered Courses");
        System.out.println("6. View grade card");
        System.out.println("7. Make Payment");
        System.out.println("8. Logout");
        System.out.println("*****************************");
    }

    public void courseRegistration(String studentId) throws IOException{
        System.out.println("Enter your full name for registration: ");
        String username= reader.readLine();
        studentInterfaceImpl.register(studentId, username);
        System.out.println(username+" has been successfully registered");
    }

    public void addCourse(String studentId) throws IOException{
        System.out.println("Enter the course code you want to add: ");
        String course= reader.readLine();
        studentInterfaceImpl.addCourse(studentId, adminInterfaceImpl.getCourseFromId(course));
        System.out.println(adminInterfaceImpl.getCourseFromId(course).getCourseName()+" added successfully");
    }
    public void dropCourse(String studentId) throws IOException{
        System.out.println("Deleting course");
        System.out.println("Enter the course code you want to drop: ");
        String course= reader.readLine();
        studentInterfaceImpl.dropCourse(studentId, adminInterfaceImpl.getCourseFromId(course));
        System.out.println(adminInterfaceImpl.getCourseFromId(course).getCourseName()+" dropped successfully");
    }
    public void viewCourse(){
        System.out.println("Show Course Catalog");
        adminInterfaceImpl.viewCourses();
    }
    public void viewRegisteredCourses(String studentId){
        studentInterfaceImpl.viewCourses(studentId);
    }

    public void viewGradeCard(){
        System.out.println("Grade Card yet to be generated......");
    }
    public void makePayment(){
        System.out.println("Payments options are yet to be declared");
    }

    public void logout(){
        System.out.println("Logging you out...");
    }

}