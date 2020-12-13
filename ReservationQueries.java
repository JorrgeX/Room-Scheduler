/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
/**
 *
 * @author george
 */
public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement getReservationList;
    private static ResultSet resultSet;
    private static PreparedStatement getRoomList;
    private static PreparedStatement addReservation;
    
    public static ArrayList<ReservationEntry> getReservationByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        
        try{
            getReservationList = connection.prepareStatement("select NAME, ROOM, SEATS, TIMESTAMP from RESERVATIONS where DATE = ?");
            getReservationList.setDate(1, date);
            resultSet = getReservationList.executeQuery();
            
            while (resultSet.next()){
                reservations.add(new ReservationEntry(resultSet.getString(1), resultSet.getString(2), date, resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    public static ArrayList<ReservationEntry> getReservationbyFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        
        try{
            getReservationList = connection.prepareStatement("select ROOM, DATE, SEATS, TIMESTAMP from RESERVATIONS where NAME = ?");
            getReservationList.setString(1, faculty);
            resultSet = getReservationList.executeQuery();
            
            while (resultSet.next()){
                reservations.add(new ReservationEntry(faculty, resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return reservations;
    }
    
    // mine
    public static ArrayList<ReservationEntry> getReservationByRoom(String room){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        
        try{
            getReservationList = connection.prepareStatement("select NAME, DATE, SEATS, TIMESTAMP from RESERVATIONS where ROOM = ? order by DATE");
            getReservationList.setString(1, room);
            resultSet = getReservationList.executeQuery();
            
            while (resultSet.next()){
                reservations.add(new ReservationEntry(resultSet.getString(1), room, resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return reservations;
    }
            
    public static ArrayList<RoomEntry> getRoomsReservedByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        
        try{
            getRoomList = connection.prepareStatement("select ROOM, SEATS from RESERVATIONS where DATE = ?");
            getRoomList.setDate(1, date);
            resultSet = getRoomList.executeQuery();
            
            while (resultSet.next()){
                rooms.add(new RoomEntry(resultSet.getString(1), resultSet.getInt(2)));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static void addReservationEntry(ReservationEntry reservation){
        connection = DBConnection.getConnection();

        try{
            addReservation = connection.prepareStatement("insert into RESERVATIONS (NAME, ROOM, DATE, SEATS, TIMESTAMP) values (?, ?, ?, ?, ?)");
            addReservation.setString(1, reservation.getFaculty());
            addReservation.setString(2, reservation.getRoom());
            addReservation.setDate(3, reservation.getDate());
            addReservation.setInt(4, reservation.getSeats());
            addReservation.setTimestamp(5, reservation.getTimestamp());
            addReservation.executeUpdate();
        }

        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
   
    public static void deleteReservation(ReservationEntry reservation){
        connection = DBConnection.getConnection();
        
        try{
            getRoomList = connection.prepareStatement("delete from RESERVATIONS where TIMESTAMP = ?");
            getRoomList.setTimestamp(1, reservation.getTimestamp());
            getRoomList.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
