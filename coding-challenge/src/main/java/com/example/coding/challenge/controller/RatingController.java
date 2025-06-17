package com.example.coding.challenge.controller;

import com.example.coding.challenge.models.DTOs.RatingDTO;
import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.Rating;
import com.example.coding.challenge.service.GIFService;
import com.example.coding.challenge.service.RatingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("{gifId}/rating") //Requests ending in /{gifId}/rating will go to this Controller
public class RatingController {
    private final RatingService ratingService;
    private final GIFService gifService;

    @Autowired
    public RatingController (RatingService ratingService, GIFService gifService) {
        this.gifService = gifService;
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
    public ResponseEntity<RatingDTO> createRating (@PathVariable String gifId, @RequestBody int rating_value) {
        gifService.saveGIF(gifId);
        Rating rate = ratingService.create(gifId, rating_value, getId());
        RatingDTO response = new RatingDTO(rate.getGif(), rate.getUser(), rate.getRating(), rate.getRatingId());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RatingDTO>> getAllRating (@PathVariable String gifId) {
        List<Rating> rate = ratingService.getAll(gifId);
        List<RatingDTO> response = new ArrayList<>();
        for (Rating temp: rate) {
            response.add(new RatingDTO(temp.getGif(), temp.getUser(), temp.getRating(), temp.getRatingId()));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<RatingDTO> getSpecificRating (@PathVariable String gifId) {
        Rating rate = ratingService.getSingle(gifId, getId());
        RatingDTO response = new RatingDTO(rate.getGif(), rate.getUser(), rate.getRating(), rate.getRatingId());
        return ResponseEntity.ok(response);

    }
    @PutMapping()
    public ResponseEntity<RatingDTO> updateSpecificRating (@PathVariable String gifId, @RequestBody int rating_value) {
        Rating rate = ratingService.update(gifId, getId(), rating_value);
        RatingDTO response = new RatingDTO(rate.getGif(), rate.getUser(), rate.getRating(), rate.getRatingId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<String> DeleteSpecificRating (@PathVariable String gifId) {
        String response = ratingService.delete(gifId, getId());
        return ResponseEntity.ok(response);
    }
}
