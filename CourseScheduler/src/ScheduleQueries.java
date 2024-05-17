/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ryan
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getSchedule;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement changeScheduleEntryStatus;
    private static ResultSet resultSet;
    
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
             java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, currentTimestamp);
            
            addScheduleEntry.executeUpdate();

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static int getScheduledStudentCount(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int scheduledCount = 0;
        try
        {
            getSchedule = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode"))){                  
             
                scheduledCount += 1;
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledCount;
        
    }
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentSchedule = new ArrayList<ScheduleEntry>();
        try
        {
            getSchedule = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && studentID.equals(resultSet.getString("studentid"))){                  
             
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString("semester"), resultSet.getString("coursecode"), 
                                                    resultSet.getString("studentid"), resultSet.getString("status"));
                studentSchedule.add(entry);
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentSchedule;
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduledStudents = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode"))){                  
             
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString("semester"), resultSet.getString("coursecode"), 
                                                    resultSet.getString("studentid"), resultSet.getString("status"));
                scheduledStudents.add(entry);
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
        
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedStudents = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule order by timestamp");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode")) && "W".equals(resultSet.getString("status"))){                  
             
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString("semester"), resultSet.getString("coursecode"), 
                                                    resultSet.getString("studentid"), resultSet.getString("status"));
                waitlistedStudents.add(entry);
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistedStudents;
        
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = dropStudentScheduleByCourse.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode")) && studentID.equals(resultSet.getString("studentid"))){                  
             
                    dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where coursecode = ? AND studentid = ?");
                    dropStudentScheduleByCourse.setString(1,courseCode);
                    dropStudentScheduleByCourse.setString(2,studentID);
                    dropStudentScheduleByCourse.executeUpdate();
                    
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropScheduleByCourse = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = dropScheduleByCourse.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && courseCode.equals(resultSet.getString("coursecode"))){                  
             
                    dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where coursecode = ? and semester = ?");
                    dropScheduleByCourse.setString(1,courseCode);
                    dropScheduleByCourse.setString(2,semester);
                    dropScheduleByCourse.executeUpdate();
                
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void updateScheduleEntryStatus(String semester, ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            updateScheduleEntry = connection.prepareStatement("select semester, coursecode, studentid, status from app.schedule");
            resultSet = updateScheduleEntry.executeQuery();
            
            while(resultSet.next())
            {
                if (semester.equals(resultSet.getString("semester")) && entry.getCourseCode().equals(resultSet.getString("coursecode")) 
                        && entry.getStudentID().equals(resultSet.getString("studentid"))){                    
             
                        changeScheduleEntryStatus = connection.prepareStatement("update app.schedule set status = ? where semester = ? AND coursecode = ? AND studentid = ?");
                        changeScheduleEntryStatus.setString(1,"S");
                        changeScheduleEntryStatus.setString(2,semester);
                        changeScheduleEntryStatus.setString(3,entry.getCourseCode());
                        changeScheduleEntryStatus.setString(4,entry.getStudentID());
                        changeScheduleEntryStatus.executeUpdate();
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
}
