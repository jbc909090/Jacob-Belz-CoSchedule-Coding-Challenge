package com.example.coding.challenge.models.DTOs;

public class GifDTO {
    private String comment="";
    private String url;
    private int rating=0;

    //----------------- all boilerplate below this line -----------
    public GifDTO(String url) {
        this.url = url;
    }
    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    public String getComment() {
        return comment;
    }

    public String getUrl() {
        return url;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GifDTO{" +
                "comment='" + comment + '\'' +
                ", url='" + url + '\'' +
                ", rating=" + rating +
                '}';
    }
}
