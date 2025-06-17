package com.example.coding.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.Rating;
import com.example.coding.challenge.repository.GIFRepository;
import com.example.coding.challenge.repository.RatingRepository;
import com.example.coding.challenge.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        Optional<User> u = userRepository.findById(userId);
        if (u.isPresent() && g.isPresent()) {
            Rating rating = new Rating(g.get(), rating_value, u.get());
            Optional<Rating> check = ratingRepository.findByGifAndUser_id(rating.getGif(), userId);
            if (check.isEmpty()) {
                //aka if you haven't already rated this GIF, save the rating else return null
                return ratingRepository.save(rating);
            }
        }
        return null;
    }

    public List<Rating> getAll(String gifId) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        List<Rating> list = new ArrayList<>();
        if (g.isPresent()) {
            list = ratingRepository.findAllByGif(g.get());
        }
        return list;
    }

    public Rating getSingle(String gifId, int userId) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        if(g.isPresent()) {
            Optional<Rating> rating = ratingRepository.findByGifAndUser_id(g.get(), userId);
            if (rating.isPresent()) {
                // since you can only rate a gif once this works
                return rating.get();
            }
        }
        return null;
    }
    public Rating update(String gifId, int userId, int rating_value) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        if (g.isPresent()) {
            Optional<Rating> rating = ratingRepository.findByGifAndUser_id(g.get(), userId);
            if (rating.isPresent()) {
                // since you can only rate a gif once this works
                Rating updated = rating.get();
                updated.setRating(rating_value);
                return ratingRepository.save(updated);
            }
        }
        return null;
    }

    public String delete(String gifId, int userId) {
        Optional<Rating> r = ratingRepository.findByGif_gifIdAndUser_id(gifId, userId);
        if (r.isPresent()) {
            ratingRepository.deleteById(r.get().getRatingId());
            return "Deleted comment";
        } else {
            return "No such comment found";
        }
    }

}
