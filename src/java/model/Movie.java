package model;

import java.sql.Date;

public class Movie {
    private String id;
    private String title;
    private String description;
    private String poster;
    private int duration;
    private Date releaseDate;
    private double rating;
    private String[] genre;
    private String actors;
    private String directors;
    private String country;
    private String trailer;
    private int age;
    private String status;

    public Movie() {
    }

    public Movie(String id, String title, String description, String poster, int duration, Date releaseDate, double rating, String[] genre, String actors, String directors, String country, String trailer, int age, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.actors = actors;
        this.directors = directors;
        this.country = country;
        this.trailer = trailer;
        this.age = age;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
