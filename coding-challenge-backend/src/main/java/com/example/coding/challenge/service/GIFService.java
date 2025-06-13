package com.example.coding.challenge.service;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.DTOs.GifDTO;
import com.example.coding.challenge.repository.GIFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class GIFService {
    GIFRepository gifRepository;

    @Autowired
    public GIFService (GIFRepository gifRepository) {
        this.gifRepository = gifRepository;
    }

    public GifDTO getById(String id) {

    }

    public List<GifDTO> search(String term) {

    }
    public List<GifDTO> trending() {

    }

    public GIF saveGIF (String id) {
        GIF gif = new GIF(id);
        return gifRepository.save(gif);
    }
}
