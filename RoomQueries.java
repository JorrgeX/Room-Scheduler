/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *
 * @author george
 */
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getRoomList;
    private static ResultSet resultSet;
    private static PreparedStatement addRoom;
    
    public static ArrayList<RoomEntry> getAllPossibleRooms(){
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> allRooms = new ArrayList<RoomEntry>();
        
        try{
            getRoomList = connection.prepareStatement("select NAME, SEATS from ROOMS order by SEATS"); //select data from database
            resultSet = getRoomList.executeQuery();
            
            while (resultSet.next()){ //if it is not empty
                RoomEntry room = new RoomEntry(resultSet.getString(1), resultSet.getInt(2));
                allRooms.add(room);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return allRooms;
    }
    
    public static void addRoom(RoomEntry room){
        connection = DBConnection.getConnection();
        
        try{
            addRoom = connection.prepareStatement("insert into ROOMS (NAME, SEATS) values (?, ?)");
            addRoom.setString(1, room.getName());
            addRoom.setInt(2, room.getSeats());
            addRoom.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropRoom(String room){
        connection = DBConnection.getConnection();
        
        try{
            getRoomList = connection.prepareStatement("delete from ROOMS where NAME = ?");
            getRoomList.setString(1, room);
            getRoomList.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
