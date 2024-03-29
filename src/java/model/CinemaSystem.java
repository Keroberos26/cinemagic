package model;

public class CinemaSystem {
    private String id;
    private String name;
    private String logo;
    private String description;
    private int numOfTheater;

    public CinemaSystem() {
    }

    public CinemaSystem(String id, String name, String logo, String description, int numOfTheater) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.numOfTheater = numOfTheater;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfTheater() {
        return numOfTheater;
    }

    public void setNumOfTheater(int numOfTheater) {
        this.numOfTheater = numOfTheater;
    }
    
    
    
    
}
