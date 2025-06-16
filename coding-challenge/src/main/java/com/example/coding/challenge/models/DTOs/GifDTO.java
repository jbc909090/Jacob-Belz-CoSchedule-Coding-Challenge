package com.example.coding.challenge.models.DTOs;

public class GifDTO {
    private String gifId;
    private String url;

    //----------------- all boilerplate below this line -----------

    public String getGifId() {
        return gifId;
    }

    public String getUrl() {
        return url;
    }

    public void setGifId(String gifId) {
        this.gifId = gifId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GifDTO{" +
                "gifId='" + gifId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
