package com.example.coding.challenge.controller;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.DTOs.GifDTO;
import com.example.coding.challenge.service.GIFService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api") //Requests ending in /api will go to this Controller
public class GIFController {
    //exists to be a middle point between the user and the API
    private final GIFService gifService;

    //Set up the beans for the service import
    @Autowired
    public GIFController(GIFService gifService) {
        this.gifService = gifService;
    }
    
    public int getId() {
        // get access to the session so we can extract the attributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        // First, we'll check if the session exists
        if (session == null) {
            throw new IllegalArgumentException("user must be logged in to do this!");
        }
        return (int) session.getAttribute("userId");
    }
    //retrieves all relevant gif ids
    @GetMapping()
    public ResponseEntity<List<GifDTO>> getAllAssociatedGifs () {
        List<GifDTO> response = gifService.allAssociatedGifs(getId());
        return ResponseEntity.ok(response);
    }
    //saves a gif to the gif id database
    @PostMapping("/save")
    public ResponseEntity<GIF> saveGIF (@RequestBody String id) {
        GIF response = gifService.saveGIF(id);
        return ResponseEntity.ok(response);
    }

}
