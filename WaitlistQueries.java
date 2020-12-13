/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
/**
 *
 * @author george
 */
public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement getWaitlist;
    private static ResultSet resultSet;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement dropWaitlist; // mine
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        
        try{
            getWaitlist = connection.prepareStatement("select FACULTY, DATE, SEATS, TIMESTAMP from WAITLIST where DATE = ? order by TIMESTAMP");
            getWaitlist.setDate(1, date);
            resultSet = getWaitlist.executeQuery();
            
            while (resultSet.next()){
                waitlist.add(new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        
        try{
            getWaitlist = connection.prepareStatement("select DATE, SEATS, TIMESTAMP from WAITLIST where FACULTY = ? order by DATE, TIMESTAMP");
            getWaitlist.setString(1, faculty);
            resultSet = getWaitlist.executeQuery();
            
            while (resultSet.next()){
                waitlist.add(new WaitlistEntry(faculty, resultSet.getDate(1), resultSet.getInt(2), resultSet.getTimestamp(3)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    // mine
    public static ArrayList<WaitlistEntry> getAllWaitlist(){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        
        try{
            getWaitlist = connection.prepareStatement("select * from WAITLIST order by TIMESTAMP");
            resultSet = getWaitlist.executeQuery();
            
            while (resultSet.next()){
                waitlist.add(new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static void addWaitlistEntry(WaitlistEntry waitlist){
        connection = DBConnection.getConnection();
        
        try{
            addWaitlist = connection.prepareStatement("insert into WAITLIST (FACULTY, DATE, SEATS, TIMESTAMP) values (?, ?, ?, ?)");
            addWaitlist.setString(1, waitlist.getName());
            addWaitlist.setDate(2, waitlist.getDate());
            addWaitlist.setInt(3, waitlist.getSeats());
            addWaitlist.setTimestamp(4, waitlist.getTimestamp());
            addWaitlist.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    //  mine
    public static void dropWaitlistEntry(WaitlistEntry waitlist){
        connection = DBConnection.getConnection();
        
        try{
            dropWaitlist = connection.prepareStatement("delete from WAITLIST where TIMESTAMP = ?");
            dropWaitlist.setTimestamp(1, waitlist.getTimestamp());
            dropWaitlist.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
