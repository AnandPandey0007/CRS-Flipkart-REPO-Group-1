package com.flipkart.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.exception.*;
import com.flipkart.service.AdminInterfaceImpl;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

    @Path("/admin")
    public class AdminRestAPI {

        AdminInterfaceImpl adminOperation = AdminInterfaceImpl.getInstance();

        /**
         * /admin/assignCourseToProfessor
         * REST-service for assigning course to professor
         * @param courseCode
         * @param professorId
         * @return
         */
        @POST
        @Path("/assignCourseToProfessor")
        @Produces(MediaType.APPLICATION_JSON)
        public Response assignCourseToProfessor(
                @NotNull
                @QueryParam("courseCode") String courseCode,
                @NotNull
                @QueryParam("professorId") String professorId) throws ValidationException {

            try {

                adminOperation.assignCourse(courseCode, professorId);
                return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + professorId).build();

            } catch (CourseNotFoundException | UserNotFoundException e) {

                return Response.status(409).entity(e.getMessage()).build();

            }
        }

        /**
         * /admin/addProfessor
         * REST-service for addding a new professor
         * @param professor
         * @return
         */
        @POST
        @Path("/addProfessor")
        @Consumes("application/json")
        @Produces(MediaType.APPLICATION_JSON)
        public Response addProfessor(@Valid Professor professor) throws ValidationException{
            try {
                adminOperation.addProfessor(professor);
                return Response.status(201).entity("Professor with professorId: " + professor.getUserId() + " added").build();
            } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
                return Response.status(409).entity(e.getMessage()).build();
            }
        }

        /**
         * /admin/viewPendingAdmissions
         * REST-service for getting all pending-approval of students
         * @return
         */
        @GET
        @Path("/viewPendingAdmissions")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> viewPendingAdmissions() {
            return adminOperation.viewPendingAdmissions();
        }

        /**
         * /admin/approveStudent
         * REST-service for approving the student admission
         * @param studentId
         * @return
         */
        @PUT
        @Path("/approveStudent")
        @Produces(MediaType.APPLICATION_JSON)
        public Response approveStudent(
                @NotNull
                @QueryParam("studentId") String studentId) throws ValidationException{
                List<Student> studentList = adminOperation.viewPendingAdmissions();
            try {
                adminOperation.approveStudent(studentId, studentList);
                return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();

            } catch (StudentNotFoundForApprovalException e) {

                return Response.status(409).entity(e.getMessage()).build();

            }

        }

        /**
         * /admin/viewCoursesInCatalogue
         * REST-service for getting courses in the catalog
         * @return
         */
        @GET
        @Path("/viewCoursesInCatalogue")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Course> viewCoursesInCatalogue() {

            return adminOperation.viewCourses();

        }

        /**
         * /admin/deleteCourse
         * REST-services for dropping a course from catalog
         * @param courseCode
         * @return
         */
        @POST
        @Path("/deleteCourse")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteCourse(
                @NotNull
                @QueryParam("courseCode") String courseCode) throws ValidationException{
            List<Course> courseList = adminOperation.viewCourses();

            try {

                adminOperation.deleteCourse(courseCode, courseList);
                return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();

            } catch (CourseNotFoundException | CourseNotDeletedException e) {

                return Response.status(409).entity(e.getMessage()).build();

            }
        }

        /**
         * /admin/addCourse
         * REST-service for adding a new course in catalog
         * @param course
         * @return
         */
        @POST
        @Path("/addCourse")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response addCourse(@Valid Course course) throws ValidationException, CourseAlreadyExistsException {
            List<Course> courseList = adminOperation.viewCourses();
            adminOperation.addCourse(course, courseList);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();
        }

        /**
         *
         * @return list of professors in the system
         */
        @GET
        @Path("/viewProfessors")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Professor> viewProfessors() {

            return adminOperation.viewProfessors();
        }
    }
