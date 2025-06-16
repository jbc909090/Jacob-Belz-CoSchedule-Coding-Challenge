package com.example.coding.challenge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "GIFs")
public class GIF {
    //this tables purpose is to store IDs of GIFs retrieved via the API
    //this is necessary so that comments and ratings maybe associated with a gif while storing GIFs with no comments or ratings
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //the id the API has associated with said
    @Column(unique = true)
    private String gifId;


    //----------------- all boilerplate below this line -----------

    public GIF(String gifId) {
        this.gifId = gifId;
    }

    public void setGifId(String gifId) {
        this.gifId = gifId;
    }

    public String getGifId() {
        return gifId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GIF{" +
                "id=" + id +
                ", gifId='" + gifId + '\'' +
                '}';
    }
}
