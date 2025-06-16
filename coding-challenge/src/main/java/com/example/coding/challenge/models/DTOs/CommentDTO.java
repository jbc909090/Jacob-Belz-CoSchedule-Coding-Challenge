package com.example.coding.challenge.models.DTOs;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.User;

public class CommentDTO {
    private int commentId;
    private int userId;
    private String gif;
    private String comment;
    //----------------- all boilerplate below this line -----------
    public CommentDTO(GIF gif, User user, String comment, int commentId) {
        this.comment = comment;
        this.gif = gif.getGifId();
        this.userId = user.getUserId();
        this.commentId = commentId;
    }
    public int getCommentId() {
        return commentId;
    }

    public int getUserId() {
        return userId;
    }

    public String getGif() {
        return gif;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
