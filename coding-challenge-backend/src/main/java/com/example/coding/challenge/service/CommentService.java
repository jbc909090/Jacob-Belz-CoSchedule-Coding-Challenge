package com.example.coding.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.repository.CommentRepository;
import com.example.coding.challenge.repository.GIFRepository;
import com.example.coding.challenge.repository.UserRepository;

public class CommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    GIFRepository gifRepository;
    
    @Autowired
    public CommentService (CommentRepository commentRepository, UserRepository userRepository, GIFRepository gifRepository) {
        this.commentRepository = commentRepository;
        this.gifRepository = gifRepository;
        this.userRepository = userRepository;
    }
    public Comment create (String gifId, String comment_text, int userId) {
        Comment comment = new Comment(gifRepository.findByGif(gifId).get(), comment_text, userRepository.findById(userId).get());
        Optional<Comment> check = commentRepository.findByGifAndUser_id(comment.getGif(), userId);
        if (check.isEmpty()) {
            //Letting a User leave multiple comments on a gif gets a lil messy with other crud operations with current design
            //TODO investigate ways to allow multiple comments from 1 user on the same gif 
            return commentRepository.save(comment);
        }
        return null;
    }
    public List<Comment> getAll(String gifId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        List<Comment> list = commentRepository.findAllByGif(gif);
        return list;
    }
    public Comment getSingle(String gifId, int userId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Comment> comment = commentRepository.findByGifAndUser_id(gif, userId);
        if (comment.isPresent()) {
            return comment.get();
        }
        return null;
    }
    
    public Comment update(String gifId, String text, int userId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Comment> comment = commentRepository.findByGifAndUser_id(gif, userId);
        if (comment.isPresent()) {
            Comment updated = comment.get();
            updated.setComment(text);
            return commentRepository.save(updated);
        }
        return null;
    }
    
    public String delete(String gifId, int userId) {
        GIF gif = gifRepository.findByGif(gifId).get();
        Optional<Comment> comment = commentRepository.findByGifAndUser_id(gif, userId);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            return "Successful";
        }
        return "No such comment found";
    }
}
