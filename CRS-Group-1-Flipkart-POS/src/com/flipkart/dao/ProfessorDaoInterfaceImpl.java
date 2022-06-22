package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.beans.Course;
import com.flipkart.beans.EnrolledStudent;
import com.flipkart.beans.Student;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.services.StudentInterfaceImpl;
import com.flipkart.utils.DBUtils;


public class ProfessorDaoInterfaceImpl implements ProfessorDaoInterface {

    @Override
    public List<Course> getCourses(String profId) {
        Connection connection=DBUtils.getConnection();
        List<Course> courseList=new ArrayList<Course>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);
            statement.setString(1, profId);
            System.out.println("query:  "+statement);
            ResultSet results=statement.executeQuery();
            System.out.println("size:    "+results.getFetchSize());
            while(results.next())
            {
                courseList.add(new Course(results.getString("courseId"),results.getString("courseName"),results.getString("professorId"),results.getInt("availableseats")));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return courseList;

    }

    @Override
    public List<EnrolledStudent> getEnrolledStudents(String courseId, String professorId) {
        Connection connection=DBUtils.getConnection();
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
            statement.setString(1, courseId);
            statement.setString(2, professorId);
            ResultSet results = statement.executeQuery();

            while(results.next())
            {
                enrolledStudents.add(new EnrolledStudent(results.getString("courseId"),results.getString("courseName"),results.getInt("studentId")));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return enrolledStudents;
    }

    public Boolean addGrade(String studentId,String courseCode,String grade) {
        Connection connection=DBUtils.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            statement.setString(3, grade);
            System.out.println("Query:    "+statement);
            int row = statement.executeUpdate();
            if(row==1)
                return true;
            else
                return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public String getProfessorById(String profId)
    {
        String name = null;
        Connection connection=DBUtils.getConnection();

        try
        {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROF_NAME);
            statement.setString(1, profId);

            ResultSet rs = statement.executeQuery();
            rs.next();
            name = rs.getString(1);

        }
        catch(SQLException e)
        {
            //logger.error(e.getMessage());
        }
        finally
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return name;
    }
}
