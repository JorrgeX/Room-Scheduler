/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author george
 */
public class ReservationEntry {
    private String faculty;
    private String room;
    private Date date;
    private Integer seats;
    private Timestamp timestamp;
    
    public ReservationEntry(String faculty, String room, Date date, Integer seats, Timestamp timestamp){
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getFaculty(){
        return this.faculty;
    }
    
    public String getRoom(){
        return this.room;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public Integer getSeats(){
        return this.seats;
    }
    
    public Timestamp getTimestamp(){
        return this.timestamp;
    }
}   
