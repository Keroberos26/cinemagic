package model;

import java.sql.Date;
import java.sql.Time;

public class Showtime {
    private String id;
    private Date showdate;
    private Time starttime;
    private Time endtime;
    private int basePrice;
    private Movie movie;
    private String roomId;
    private String roomName;

    public Showtime() {
    }

    public Showtime(String id, Date showdate, Time starttime, Time endtime, int basePrice, Movie movie, String roomId, String roomName) {
        this.id = id;
        this.showdate = showdate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.basePrice = basePrice;
        this.movie = movie;
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getShowdate() {
        return showdate;
    }

    public void setShowdate(Date showdate) {
        this.showdate = showdate;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    
    
}
