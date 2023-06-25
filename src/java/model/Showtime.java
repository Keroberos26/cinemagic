package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Showtime {
    private String id;
    private Timestamp starttime;
    private Timestamp endtime;
    private int priceN;
    private int priceV;
    private int priceC;
    private Movie movie;
    private Room room;

    public Showtime() {
    }

    public Showtime(String id, Timestamp starttime, Timestamp endtime, int priceN, int priceV, int priceC, Movie movie, Room room) {
        this.id = id;
        this.starttime = starttime;
        this.endtime = endtime;
        this.priceN = priceN;
        this.priceV = priceV;
        this.priceC = priceC;
        this.movie = movie;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public int getPriceN() {
        return priceN;
    }

    public void setPriceN(int priceN) {
        this.priceN = priceN;
    }

    public int getPriceV() {
        return priceV;
    }

    public void setPriceV(int priceV) {
        this.priceV = priceV;
    }

    public int getPriceC() {
        return priceC;
    }

    public void setPriceC(int priceC) {
        this.priceC = priceC;
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
