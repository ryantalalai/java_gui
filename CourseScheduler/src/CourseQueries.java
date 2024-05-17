/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ryan
 */
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (semester, coursecode, description, seats) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
        public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select semester, coursecode, description, seats from app.course");
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester"))){                  
             
                CourseEntry entry = new CourseEntry(resultSet.getString("semester"), resultSet.getString("coursecode"), 
                                                    resultSet.getString("description"), resultSet.getInt("seats"));
                courses.add(entry);
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
        
    }
        
        public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getAllCourses = connection.prepareStatement("select semester, coursecode, description, seats from app.course");
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester"))){             
                
                courseCodes.add(resultSet.getString("coursecode"));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
        
    }
        
        public static int getCourseSeats(String semester, String courseCode)
    {   int seats = 0;
        connection = DBConnection.getConnection();
        try
        {
            getAllCourses = connection.prepareStatement("select semester, coursecode, description, seats from app.course");
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode"))){             
                
                    seats = resultSet.getInt("seats");

                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
        
    }
        
        public static void dropCourse(String semester, String courseCode)
    {   
        connection = DBConnection.getConnection();
        try
        {
            dropCourse = connection.prepareStatement("select semester, coursecode, description, seats from app.course");
            resultSet = dropCourse.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode"))){             
                
                    dropCourse = connection.prepareStatement("delete from app.course where coursecode = ? and semester = ?");
                    dropCourse.setString(1,courseCode);
                    dropCourse.setString(2,semester);
                    dropCourse.executeUpdate();

                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }
}
