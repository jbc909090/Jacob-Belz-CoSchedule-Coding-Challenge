package com.example.coding.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.coding.challenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.repository.CommentRepository;
import com.example.coding.challenge.repository.GIFRepository;
import com.example.coding.challenge.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        Optional<User> u = userRepository.findById(userId);
        if (u.isPresent() && g.isPresent()) {
            Comment comment = new Comment(g.get(), comment_text, u.get());
            Optional<Comment> check = commentRepository.findByGifAndUser_id(comment.getGif(), userId);
            if (check.isEmpty()) {
                //Letting a User leave multiple comments on a gif gets a lil messy with other crud operations with current design
                //TODO investigate ways to allow multiple comments from 1 user on the same gif
                return commentRepository.save(comment);
            }
        }
        return null;
    }
    public List<Comment> getAll(String gifId) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        List<Comment> list = new ArrayList<>();
        if (g.isPresent()) {
            list = commentRepository.findAllByGif(g.get());
        }
        return list;
    }
    public Comment getSingle(String gifId, int userId) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        if (g.isPresent()) {
            Optional<Comment> comment = commentRepository.findByGifAndUser_id(g.get(), userId);
            if (comment.isPresent()) {
                return comment.get();
            }
        }
        return null;
    }
    
    public Comment update(String gifId, String text, int userId) {
        Optional<GIF> g = gifRepository.findByGifId(gifId);
        if (g.isPresent()) {
            Optional<Comment> comment = commentRepository.findByGifAndUser_id(g.get(), userId);
            if (comment.isPresent()) {
                Comment updated = comment.get();
                updated.setComment(text);
                return commentRepository.save(updated);
            }
        }
        return null;
    }
    
    public String delete(String gifId, int userId) {
        Optional<Comment> c = commentRepository.findByGif_gifIdAndUser_id(gifId, userId);
        if (c.isPresent()) {
            commentRepository.deleteById(c.get().getCommentId());
            return "Deleted comment";
        } else {
        return "No such comment found";
        }
    }
}
