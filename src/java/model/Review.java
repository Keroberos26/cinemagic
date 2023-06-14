package model;

import java.sql.Date;

public class Review {
    private Account acc;
    private Date date;
    private int rating;
    private String comment;

    public Review() {
    }

    public Review(Account acc, Date date, int rating, String comment) {
        this.acc = acc;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
