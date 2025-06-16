package com.example.coding.challenge.models.DTOs;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.User;

public class RatingDTO {
    private int ratingId;
    private int userId;
    private String gif;
    private int rating;
    //----------------- all boilerplate below this line -----------
    public RatingDTO(GIF gif, User user, int rating, int ratingId){
        this.gif = gif.getGifId();
        this.userId = user.getUserId();
        this.rating = rating;
        this.ratingId = ratingId;
    }
    public int getUserId() {
        return userId;
    }

    public String getGif() {
        return gif;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
