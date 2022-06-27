package com.flipkart.constant;
/**
 * 
 *
 *
 */

public class SQLQueriesConstants {
	
	//AdminDao Queries
	public static final String DELETE_COURSE_QUERY = "delete from coursecatalog where courseid = ?";
	public static final String ADD_COURSE_QUERY = "insert into coursecatalog(courseid, courseName, professorid, available) values (?, ?, ?, ?)";
	public static final String VIEW_PENDING_ADMISSION_QUERY = "select studentId, studentname from student where isApproved = 0";
	public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
	public static final String ADD_USER_QUERY = "insert into user(userId, password, role) values (?, ?, ?)";
	public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, department, designation) values (?, ?, ?)";
	public static final String ASSIGN_COURSE_QUERY = "update coursecatalog set professorId = ? where courseCode = ?";
	public static final String VIEW_COURSE_QUERY = "select courseid, courseName, professorId from Course where catalogId = ?";
	public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address, country from Professor natural join User";
	
	public static final String ADD_STUDENT="insert into student (studentid, studentname , isapproved) values (?,?,?)";
	public static final String VERIFY_CREDENTIALS="select password from user where userid = ?";
	public static final String GET_ROLE="select role from user where userId = ? ";
	public static final String IS_APPROVED="select isapproved from student where studentId = ? ";
	public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
	public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
	public static final String GET_PROF_NAME = "select name from user where userId = ?";
		
	// Student Queries


	public static final String VIEW_REGISTERED_COURSES=" select * from coursecatalog inner join registeredcourses on coursecatalog.courseid = registeredcourses.courseId where registeredcourses.studentId = ?";
	public static final String VIEW_AVAILABLE_COURSES=" select * from coursecatalog";

	public static final String CHECK_COURSE_AVAILABILITY=" select courseid from registeredcourses where studentId = ? ";
	public static final String DECREMENT_COURSE_SEATS="update coursecatalog set availableseats = availableseats-1 where courseId = ? ";
	public static final String ADD_COURSE="insert into registeredcourses (studentId,courseId) values ( ? , ? )";
	public static final String DROP_COURSE_QUERY = "delete from registeredcourses where courseId = ? AND studentId = ?;";
	public static final String INCREMENT_SEAT_QUERY  = "update course set availableseats = availableseats + 1 where  courseid = ?;";
	public static final String CALCULATE_FEES  = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourses where studentId = ?);";
	public static final String VIEW_GRADE = "select coursecatalog.courseId,coursecatalog.courseName,registeredcourses.grade from course inner join registeredcourses on course.courseCode = registeredcourses.courseId where registeredcourses.studentId = ?;";
	public static final String GET_SEATS = "select availableseats from coursecatalog where courseid = ?;";
	public static final String INSERT_PAYMENT = "insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);";
	public static final String INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
	public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";
	public static final String ADD_GRADE="update registeredcourses set Grade=? where courseCode=? and studentId=?";
	public static final String GET_COURSES="select * from coursecatalog where professorId=?";
	public static final String GET_REGISTRATION_STATUS=" select isapproved from student where studentId = ? ";
	public static final String SET_REGISTRATION_STATUS="update student set isapproved=true  where studentId=?";
	public static final String GET_ENROLLED_STUDENTS="select coursecatalog.courseId, coursecatalog.courseName,registeredcourses.studentId from coursecatalog inner join registeredcourses on coursecatalog.courseId = registeredcourses.courseId where coursecatalog.professorId = ? order by coursecatalog.courseId";
	public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from registeredcourses where studentId = ? ";
	public static final String IS_REGISTERED=" select courseId from registeredcourses where courseid=? and studentId=? ";
}
