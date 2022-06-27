package com.flipkart.rest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.service.StudentInterfaceImpl;
import com.flipkart.service.UserInterfaceImpl;
import com.flipkart.bean.Student;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;
    @Path("/user")
    public class UserRestAPI {
        StudentInterfaceImpl studentInterface= StudentInterfaceImpl.getInstance();
        UserInterfaceImpl userInterface = UserInterfaceImpl.getInstance();
        /**
         *
         * @param userId: email address of the user
         * @param newPassword: new password to be stored in db.
         * @return @return 201, if password is updated, else 500 in case of error
         */
        @GET
        @Path("/updatePassword")
        @Produces(MediaType.APPLICATION_JSON)
        public Response updatePassword(
                @NotNull
                @QueryParam("userId") String userId,
                @NotNull
                @QueryParam("newPassword") String newPassword) throws ValidationException {

            if(userInterface.updatePassword(userId, newPassword)) {
                return Response.status(201).entity("Password updated successfully! ").build();
            }
            else {
                return Response.status(500).entity("Something went wrong, please try again!").build();
            }
        }

        /**
         *
         * @param userId
         * @param password
         * @return
         */


        @POST
        @Path("/login")
        @Consumes("application/json")
        public Response verifyCredentials(
                @NotNull
                @QueryParam("userId") String userId,
                @NotNull
                @QueryParam("password") String password) throws ValidationException {
            try {
                boolean loggedin=userInterface.verifyCredentials(userId, password);
                System.out.println(loggedin);
                if(loggedin) {
                    String role=userInterface.getRole(userId);
                    Role userRole=Role.stringToName(role);
                    switch(userRole) {
                        case STUDENT:
                            String studentId=userId;
                            boolean isApproved=studentInterface.isApproved(studentId);
                            if(!isApproved) {
                                return Response.status(200).entity("Login unsuccessful! Student "+userId+" has not been approved by the administration!" ).build();
                            }
                            break;

                    }
                    return Response.status(200).entity("Login successful").build();
                }
                else {
                    return Response.status(500).entity("Invalid credentials!").build();
                }
            } catch (UserNotFoundException e) {
                return Response.status(500).entity(e.getMessage()).build();
            }

        }

        /**
         *
         * @param userId
         * @return
         * @throws ValidationException
         */
        @GET
        @Path("/getRole")
        @Produces("application/json")
        public String getRole(
                @NotNull
                @QueryParam("userId") String userId ) throws ValidationException{
            return userInterface.getRole(userId);
        }

        /**
         *
         * @param student
         * @return 201, if user is created, else 500 in case of error
         */
        @POST
        @Path("/studentRegistration")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response register(@Valid Student student) {

            try {
                studentInterface.register(student.getStudentName(), student.getUserId(), student.getPassword());
            }
            catch(Exception ex) {
                return Response.status(500).entity("Something went wrong! Please try again.").build();
            }
            return Response.status(201).entity("Registration Successful for "+student.getUserId()).build();
        }
    }

