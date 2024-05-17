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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentid, firstname, lastname) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> student = new ArrayList<StudentEntry>();
        try
        {
            getStudentList = connection.prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = getStudentList.executeQuery();
            
            while(resultSet.next())
            {
                StudentEntry entry = new StudentEntry(resultSet.getString("studentid"), resultSet.getString("firstname"), resultSet.getString("lastname"));
                student.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
        
    }
    
    public static StudentEntry getStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        StudentEntry student = null;
        try
        {
            getStudent = connection.prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next())
            {
                if (studentID.equals(resultSet.getString("studentid"))){  
                    
                student = new StudentEntry(resultSet.getString("studentid"), resultSet.getString("firstname"), resultSet.getString("lastname"));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return student;
        
    }
    
    public static void dropStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = dropStudent.executeQuery();
            
            while(resultSet.next())
            {
                if (studentID.equals(resultSet.getString("studentid"))){  
                    dropStudent = connection.prepareStatement("delete from app.student where studentid = ?");
                    dropStudent.setString(1,studentID);
                    dropStudent.executeUpdate();
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
}
