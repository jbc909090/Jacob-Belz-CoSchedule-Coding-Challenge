package com.example.coding.challenge.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;

    //foreign key to users
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    // foreign key to the associated GIF
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "gifId")
    private GIF gif;

    //the actual rating out of 5 stars
    private int rating;

    //----------------- all boilerplate below this line -----------
    public Rating(GIF gif, int rating, User user) {
        this.gif = gif;
        this.rating = rating;
        this.user = user;
    }
    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGif(GIF gif) {
        this.gif = gif;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public User getUser() {
        return user;
    }

    public GIF getGif() {
        return gif;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", user=" + user +
                ", gif=" + gif +
                ", rating=" + rating +
                '}';
    }
}
