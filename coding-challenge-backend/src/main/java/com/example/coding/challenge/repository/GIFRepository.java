package com.example.coding.challenge.repository;

import com.example.coding.challenge.models.GIF;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GIFRepository extends JpaRepository<GIF, Integer> {
    Optional<GIF> findByGif(String gif);
}
