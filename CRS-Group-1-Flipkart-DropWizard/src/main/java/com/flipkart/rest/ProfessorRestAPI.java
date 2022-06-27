package com.flipkart.rest;

import com.flipkart.service.ProfessorInterfaceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.validator.ProfessorValidator;

    @Path("/professor")
    public class ProfessorRestAPI {

        ProfessorInterfaceImpl professorInterfaceImpl= ProfessorInterfaceImpl.getInstance();
        @GET
        @Path("/getEnrolledStudents")
        @Produces(MediaType.APPLICATION_JSON)
        public List<EnrolledStudent> viewEnrolledStudents(
                @NotNull
                @Email(message = "Invalid Professor ID: Not in email format")
                @QueryParam("profId") String profId) throws ValidationException	{

            List<EnrolledStudent> students=new ArrayList<EnrolledStudent>();
            try
            {
                students=professorInterfaceImpl.viewEnrolledStudents(profId);
            }
            catch(Exception ex) {
                return null;
            }
            return students;
        }

        @GET
        @Path("/getCourses")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Course> getCourses(
                @NotNull
                @Email(message = "Invalid Professor ID: Not in email format")
                @QueryParam("profId") String profId) throws ValidationException	{

            List<Course> courses=new ArrayList<Course>();
            try
            {
                courses=professorInterfaceImpl.getCourses(profId);
            }
            catch(Exception ex)
            {
                return null;
            }
            return courses;

        }

        @POST
        @Path("/addGrade")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addGrade(
                @NotNull
                @QueryParam("studentId") String studentId,

                @NotNull
                @Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character")
                @QueryParam("courseCode") String courseCode,

                @NotNull
                @Email(message = "Invalid Professor ID: Not in email format")
                @QueryParam("profId") String profId,

                @QueryParam("grade") String grade) throws ValidationException  	{

            try {
                List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
                enrolledStudents=professorInterfaceImpl.viewEnrolledStudents(profId);
                List<Course> coursesEnrolled=new ArrayList<Course>();
                coursesEnrolled	=professorInterfaceImpl.getCourses(profId);
                if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode)) {
                    professorInterfaceImpl.addGrade(studentId, courseCode, grade);
                }
                else
                {
                    //error code
                    return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
                }
            }
            catch(Exception ex)
            {
                return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
            }
            return Response.status(200).entity( "Grade updated for student: "+studentId).build();
        }
    }

