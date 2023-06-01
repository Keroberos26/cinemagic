package model;

public class Room {
    private String id;
    private String name;
    private String theaterid;

    public Room() {
    }

    public Room(String id, String name, String theaterid) {
        this.id = id;
        this.name = name;
        this.theaterid = theaterid;
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

    public String getTheaterid() {
        return theaterid;
    }

    public void setTheaterid(String theaterid) {
        this.theaterid = theaterid;
    }

    
}
