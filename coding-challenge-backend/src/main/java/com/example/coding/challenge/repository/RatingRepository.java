package com.example.coding.challenge.repository;

import com.example.coding.challenge.models.GIF;
import com.example.coding.challenge.models.Rating;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findAllByGif(GIF gif);
    Optional<Rating> findByGifAndUser_id(GIF gif, int id);
}
