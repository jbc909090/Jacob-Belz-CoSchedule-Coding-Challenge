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

import java.util.ArrayList;
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
    public List<GifDTO> allAssociatedGifs(int userId) {
        List<Rating> ratings = ratingRepository.findByUser_id(userId);
        List<Comment> comments = commentRepository.findByUser_id(userId);
        List<String> temp = new ArrayList<>();
        List<GifDTO> gifs = new ArrayList<>();
        List<GifDTO> gifs2 = new ArrayList<>();
        for (Rating r : ratings) {
            temp.add(r.getGif().getGifId());
            GifDTO g = new GifDTO(r.getGif().getGifId());
            g.setRating(r.getRating());
            gifs.add(g);
        }
        for(Comment c: comments) {
            if (temp.contains(c.getGif().getGifId())) {
                int i = temp.indexOf(c.getGif().getGifId());
                GifDTO g = gifs.get(i);
                g.setComment(c.getComment());
                gifs2.add(g);
                gifs.remove(i);
                temp.remove(i);
            } else {
                GifDTO g = new GifDTO(c.getGif().getGifId());
                g.setComment(c.getComment());
                gifs2.add(g);
            }
        }
        gifs.addAll(gifs2);
        return gifs;
    }
    public GIF saveGIF(String id) {
        Optional<GIF> check = gifRepository.findByGifId(id);
        if (check.isPresent()) {
            return null;
        }
        GIF gif = new GIF(id);
        return gifRepository.save(gif);
    }
}
