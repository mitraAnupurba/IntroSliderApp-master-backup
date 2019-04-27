package com.example.introsliderapp.model;

public class Guest {

    int ratingGuest;
    String reviewGuest;

    public Guest(int ratingGuest, String reviewGuest){

            this.ratingGuest = ratingGuest;
            this.reviewGuest = reviewGuest;

    }

    public Guest(){}

    public int getRatingGuest() {
        return ratingGuest;
    }

    public void setRatingGuest(int ratingGuest) {
        this.ratingGuest = ratingGuest;
    }

    public String getReviewGuest() {
        return reviewGuest;
    }

    public void setReviewGuest(String reviewGuest) {
        this.reviewGuest = reviewGuest;
    }
}
