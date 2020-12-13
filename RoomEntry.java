/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author george
 */
public class RoomEntry {
    private String name;
    private Integer seats;
    
    public RoomEntry(String name, Integer seats){
        this.name = name;
        this.seats = seats;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Integer getSeats(){
        return this.seats;
    }
}
