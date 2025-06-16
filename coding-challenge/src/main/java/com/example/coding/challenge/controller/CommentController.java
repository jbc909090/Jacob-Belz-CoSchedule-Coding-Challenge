package com.example.coding.challenge.controller;


import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.DTOs.CommentDTO;
import com.example.coding.challenge.service.CommentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
@RequestMapping("{gifId}/comment") //Requests ending in /{gifId}/comment will go to this Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController (CommentService commentService) {
        this.commentService = commentService;
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
    public ResponseEntity<CommentDTO> createComment (@PathVariable String gifId, @RequestBody String comment_text) {
        Comment comment = commentService.create(gifId, comment_text, getId());
        CommentDTO response = new CommentDTO(comment.getGif(), comment.getUser(), comment.getComment(), comment.getCommentId());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>> getAllComment (@PathVariable String gifId) {
        List<Comment> comment = commentService.getAll(gifId);
        List<CommentDTO> response = new ArrayList<>();
        for (Comment temp: comment) {
            response.add(new CommentDTO(temp.getGif(), temp.getUser(), temp.getComment(), temp.getCommentId()));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<CommentDTO> getSpecificComment (@PathVariable String gifId) {
        Comment comment = commentService.getSingle(gifId, getId());
        CommentDTO response = new CommentDTO(comment.getGif(), comment.getUser(), comment.getComment(), comment.getCommentId());
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<CommentDTO> updateSpecificComment (@PathVariable String gifId, @RequestBody String comment_text) {
        Comment comment = commentService.update(gifId, comment_text, getId());
        CommentDTO response = new CommentDTO(comment.getGif(), comment.getUser(), comment.getComment(), comment.getCommentId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteSpecificComment (@PathVariable String gifId) {
        String response = commentService.delete(gifId, getId());
        return ResponseEntity.ok(response);
    }
}

