package model;

public class Room {
    private String id;
    private String name;
    private Seat[][] seatMap;

    public Room() {
    }

    public Room(String id, String name, Seat[][] seatMap) {
        this.id = id;
        this.name = name;
        this.seatMap = seatMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seat[][] getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Seat[][] seatMap) {
        this.seatMap = seatMap;
    }
    
    
}
