package com.flipkart.rest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.service.ProfessorInterfaceImpl;
import com.flipkart.service.RegistrationInterfaceImpl;
import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
    @Path("/student")
    public class StudentRestAPI {

        RegistrationInterfaceImpl registrationInterfaceImpl = RegistrationInterfaceImpl.getInstance();
        ProfessorInterfaceImpl professorInterfaceImpl = ProfessorInterfaceImpl.getInstance();
        
        /**
         * Method to handle API request for course registration
         * @param courseList
         * @param studentId
         * @throws ValidationException
         * @return
         */

        @POST
        @Path("/registerCourses")
        @Consumes("application/json")
        @Produces(MediaType.APPLICATION_JSON)
        public Response registerCourses(List<String> courseList,
                                        @NotNull
                                        @QueryParam("studentId") String studentId)	throws ValidationException{


            try {
                if(registrationInterfaceImpl.getRegistrationStatus(studentId) == true)
                    return Response.status(200).entity("Student "+ studentId+" is already registered.").build();

                List<Course> availableCourseList = registrationInterfaceImpl.viewCourses();
                Set<String> hash_set = new HashSet<String>();

                for(String courseCode:courseList) {
                    registrationInterfaceImpl.checkCourse(courseCode, studentId, availableCourseList);

                    if(!hash_set.add(courseCode))
                        return Response.status(500).entity("Duplicate value  : "+courseCode).build();
                }

                for(String courseCode:courseList)
                    registrationInterfaceImpl.addCourse(courseCode, studentId, availableCourseList);

                registrationInterfaceImpl.setRegistrationStatus(studentId);
            }
            catch (CourseLimitExceedException | SeatNotAvailableException | CourseNotFoundException | CourseAlreadyRegisteredException e)
            {
                System.out.println(e.getMessage());
                return Response.status(500).entity(e.getMessage()).build();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            return Response.status(201).entity( "Registration Successful").build();
        }


        /**
         * Handles api request to add a course
         * @param courseCode
         * @param studentId
         * @return
         * @throws ValidationException
         */
        @POST
        @Path("/addCourse")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addCourse(
                @NotNull
                @QueryParam("courseCode") String courseCode,
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException{
            try {
                if(registrationInterfaceImpl.getRegistrationStatus(studentId) == false)
                    return Response.status(200).entity("Student course registration is pending").build();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try{
                List<Course> availCourseList = null;
                try {
                    availCourseList = registrationInterfaceImpl.viewCourses();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                registrationInterfaceImpl.checkCourse(courseCode, studentId, availCourseList);
                try {
                    registrationInterfaceImpl.addCourse(courseCode, studentId, availCourseList);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return Response.status(201).entity( "You have successfully added Course : " + courseCode).build();
            }
            catch (CourseLimitExceedException | SeatNotAvailableException | CourseNotFoundException | CourseAlreadyRegisteredException e)
            {
                System.out.println(e.getMessage());

                return Response.status(500).entity(e.getMessage()).build();
            }

        }


        /**
         * Handles API request to drop a course
         * @param courseCode
         * @param studentId
         * @return
         * @throws ValidationException
         */
        @DELETE
        @Path("/dropCourse")
        @Produces(MediaType.APPLICATION_JSON)
        public Response dropCourse(
                @NotNull
                @Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character")
                @QueryParam("courseCode") String courseCode,
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException, SQLException {

            try {
                if(registrationInterfaceImpl.getRegistrationStatus(studentId) == false)
                    return Response.status(200).entity("Student course registration is pending").build();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try{

                List<Course>registeredCourseList = registrationInterfaceImpl.viewRegisteredCourses(studentId);
                try {
                    registrationInterfaceImpl.dropCourse(courseCode, studentId, registeredCourseList);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return Response.status(201).entity( "You have successfully dropped Course : " + courseCode).build();
            }
            catch(CourseNotFoundException e)
            {
                System.out.println(e.getMessage());
                return Response.status(501).entity("You have not registered for course : " + e.getCourseCode()).build();
            }

        }


        /**
         * Method handles API request to view the list of available courses for a student
         * @return
         * @throws ValidationException
         */

        @GET
        @Path("/viewAvailableCourses")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Course> viewCourse() throws ValidationException, SQLException {
            return registrationInterfaceImpl.viewCourses();
        }

        /**
         * Method handles API request to view the list of registered courses for a student
         * @param studentId
         * @return
         * @throws ValidationException
         */
        @GET
        @Path("/viewRegisteredCourses")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Course> viewRegisteredCourse(
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException{

            try {
                return registrationInterfaceImpl.viewRegisteredCourses(studentId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Method handles API request to make payment for registered courses
         * @param studentId
         * @param paymentMode
         * @return
         * @throws ValidationException
         */

        /**
         * Method handles request to display the total fees for student
         * @param studentId
         * @return
         * @throws ValidationException
         */
        @GET
        @Path("/calculateFees")
        public Response calculateFee(
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException{

            try {
                if(registrationInterfaceImpl.getRegistrationStatus(studentId) == false)
                    return Response.status(200).entity("Student course registration is pending").build();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            double fee = 0;
            try {
                fee = registrationInterfaceImpl.calculateFee(studentId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return Response.status(200).entity("Your total fee  = " + fee + "\n").build();
        }

        /**
         * Method handles request to display the grade card for student
         * @param studentId
         * @return
         * @throws ValidationException
         */

        @GET
        @Path("/viewGradeCard")
        @Produces(MediaType.APPLICATION_JSON)
        public List<StudentGrade> viewGradeCard(
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException{
            List<StudentGrade> grade_card = null;
            try {
                grade_card = registrationInterfaceImpl.viewGradeCard(studentId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return grade_card;

        }
    }