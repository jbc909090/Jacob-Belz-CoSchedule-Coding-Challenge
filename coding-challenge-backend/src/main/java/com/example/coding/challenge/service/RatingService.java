package com.example.coding.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.Rating;
import com.example.coding.challenge.repository.GIFRepository;
import com.example.coding.challenge.repository.RatingRepository;
import com.example.coding.challenge.repository.UserRepository;

public class RatingService {
    RatingRepository ratingRepository;
    UserRepository userRepository;
    GIFRepository gifRepository;

    @Autowired
    public RatingService (RatingRepository ratingRepository, UserRepository userRepository, GIFRepository gifRepository) {
        this.ratingRepository = ratingRepository;
        this.gifRepository = gifRepository;
        this.userRepository = userRepository;
    }

    public Rating create(String gifId, int rating_value, int userId) {
        Rating rating = new Rating(gifRepository.findByGif(gifId).get(), rating_value, userRepository.findById(userId).get());
        Optional<Rating> check = ratingRepository.findByGifAndUser_id(rating.getGif(), userId);
        if (check.isEmpty()) {
            //aka if you haven't already rated this GIF, save the rating else return null
            return ratingRepository.save(rating);
        }
        return null;
    }

    public List<Rating> getAll(String gifId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        List<Rating> list = ratingRepository.findAllByGif(gif);
        return list;
    }

    public Rating getSingle(String gifId, int userId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Rating> rating = ratingRepository.findByGifAndUser_id(gif, userId);
        if (rating.isPresent()) {
            // since you can only rate a gif once this works
            return rating.get();
        }
        return null;
    }
    public Rating update(String gifId, int userId, int rating_value) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Rating> rating = ratingRepository.findByGifAndUser_id(gif, userId);
        if (rating.isPresent()) {
            // since you can only rate a gif once this works
            Rating updated = rating.get();
            updated.setRating(rating_value);
            return ratingRepository.save(updated);
        }
        return null;
    }

    public String delete(String gifId, int userId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Rating> rating = ratingRepository.findByGifAndUser_id(gif, userId);
        if (rating.isPresent()) {
            // since you can only rate a gif once this works
            ratingRepository.delete(rating.get());
            return "Successful";
        }
        return "No such rating found";
    }

}
