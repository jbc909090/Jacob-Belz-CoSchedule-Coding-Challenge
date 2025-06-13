package com.example.coding.challenge.controller;

import com.example.coding.challenge.models.Rating;
import com.example.coding.challenge.service.RatingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping("{gifId}/rating") //Requests ending in /{gifId}/rating will go to this Controller
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController (RatingService ratingService) {
        this.ratingService = ratingService;
    }

    public int getId () {
        //get access to the session so we can extract the attributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        //First, we'll check if the session exists
        if(session == null){
            throw new IllegalArgumentException("user must be logged in to do this!");
        }
        return (int) session.getAttribute("userId");
    }

    @PostMapping
    public ResponseEntity<Rating> createRating (@PathVariable String gifId, @RequestBody int rating_value) {
        Rating rating = ratingService.create(gifId, rating_value, getId());
        return ResponseEntity.ok(rating);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRating (@PathVariable String gifId) {
        List<Rating> list = ratingService.getAll(gifId);
        return ResponseEntity.ok(list);
    }
    @GetMapping
    public ResponseEntity<Rating> getSpecificRating (@PathVariable String gifId) {
        Rating rate = ratingService.getSingle(gifId, getId());
        return ResponseEntity.ok(rate);

    }
    @PutMapping()
    public ResponseEntity<Rating> updateSpecificRating (@PathVariable String gifId, @RequestBody int rating_value) {
        Rating rate = ratingService.update(gifId, getId(), rating_value);
        return ResponseEntity.ok(rate);
    }

    @DeleteMapping()
    public ResponseEntity<String> DeleteSpecificRating (@PathVariable String gifId) {
        String response = ratingService.delete(gifId, getId());
        return ResponseEntity.ok(response);
    }
}
