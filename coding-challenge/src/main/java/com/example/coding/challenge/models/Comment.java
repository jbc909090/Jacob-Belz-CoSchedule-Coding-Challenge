package com.example.coding.challenge.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Comments")
public class Comment {
    //private key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    //foreign key to users
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    // foreign key to the associated GIF
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gifId")
    private GIF gif;

    //the actual comment text
    private String comment = "";

    //----------------- all boilerplate below this line -----------
    public Comment() {}
    public Comment(GIF gif, String comment, User user) {
        this.comment = comment;
        this.gif = gif;
        this.user = user;
    }
    public int getCommentId() {
        return commentId;
    }

    public User getUser() {
        return user;
    }

    public GIF getGif() {
        return gif;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGif(GIF gif) {
        this.gif = gif;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", user=" + user +
                ", gif=" + gif +
                ", comment='" + comment + '\'' +
                '}';
    }
}
