package model;

public class Seat {
    private String id;
    private String seatNum;
    private String type;

    public Seat() {
    }

    public Seat(String id, String seatNum, String type) {
        this.id = id;
        this.seatNum = seatNum;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
