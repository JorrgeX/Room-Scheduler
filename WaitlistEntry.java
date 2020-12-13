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
public class WaitlistEntry {
    private String name;
    private Date date;
    private Integer seats;
    private Timestamp timestamp;
    
    public WaitlistEntry(String name, Date date, Integer seats, Timestamp timestamp){
        this.name= name;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getName(){
        return this.name;
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
