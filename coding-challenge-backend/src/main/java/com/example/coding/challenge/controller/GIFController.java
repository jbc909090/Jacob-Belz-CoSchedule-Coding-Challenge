package com.example.coding.challenge.controller;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.DTOs.GifDTO;
import com.example.coding.challenge.service.GIFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") //Requests ending in /api will go to this Controller
public class GIFController {
    //exists to be a middle point between the user and the API
    private final GIFService gifService;

    //Set up the beans for the service import
    @Autowired
    public GIFController(GIFService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GifDTO> getGIFById(@PathVariable String id) {
        GifDTO gif = gifService.getById(id);
        return ResponseEntity.ok(gif);
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<GifDTO>> getGIFsBySearch(@PathVariable String term) {
        List<GifDTO> gif = gifService.search(term);
        return ResponseEntity.ok(gif);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<GifDTO>> getGIFsByTrending() {
        List<GifDTO> gif = gifService.trending();
        return ResponseEntity.ok(gif);
    }
    //saves a gif to the gif id database
    @PostMapping("/save/{id}")
    public ResponseEntity<GIF> saveGIF (@PathVariable String id) {
        GIF response = gifService.saveGIF(id);
        return ResponseEntity.ok(response);
    }

}
