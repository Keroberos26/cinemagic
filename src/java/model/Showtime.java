package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Showtime {
    private String id;
    private Date showdate;
    private Time starttime;
    private Time endtime;
    private int basePrice;
    private Movie movie;
    private Room room;

    public Showtime() {
    }

    public Showtime(String id, Date showdate, Time starttime, Time endtime, int basePrice, Movie movie, Room room) {
        this.id = id;
        this.showdate = showdate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.basePrice = basePrice;
        this.movie = movie;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Showtime other = (Showtime) obj;
        return Objects.equals(this.id, other.id);
    }
   
}
