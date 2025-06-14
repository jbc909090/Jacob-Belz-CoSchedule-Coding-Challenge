package com.example.coding.challenge.service;

import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.Rating;
import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.DTOs.GifDTO;
import com.example.coding.challenge.repository.CommentRepository;
import com.example.coding.challenge.repository.GIFRepository;
import com.example.coding.challenge.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GIFService {
    GIFRepository gifRepository;
    RatingRepository ratingRepository;
    CommentRepository commentRepository;

    @Autowired
    public GIFService (GIFRepository gifRepository, RatingRepository ratingRepository, CommentRepository commentRepository) {
        this.gifRepository = gifRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }
    public List<String> allAssociatedGifs(int userId) {
        List<Rating> ratings = ratingRepository.findByUser_id(userId);
        List<Comment> comments = commentRepository.findByUser_id(userId);
        List<String> response = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for(Comment c: comments) {
            response.add(c.getGif().getGifId());
        }
        for (Rating r : ratings) {
            temp.add(r.getGif().getGifId());
        }
        response.removeAll(temp);
        response.addAll(temp);
        return response;
    }
    public GIF saveGIF(String id) {
        Optional<GIF> check = gifRepository.findByGif(id);
        if (check.isPresent()) {
            return null;
        }
        GIF gif = new GIF(id);
        return gifRepository.save(gif);
    }
}
