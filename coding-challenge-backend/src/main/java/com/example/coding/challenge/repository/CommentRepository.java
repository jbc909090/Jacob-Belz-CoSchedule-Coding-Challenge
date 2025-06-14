package com.example.coding.challenge.repository;

import com.example.coding.challenge.models.Comment;
import com.example.coding.challenge.models.GIF;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByGif(GIF gif);
    Optional<Comment> findByGifAndUser_id(GIF gif, int id);
    List<Comment> findByUser_id(int id);
}
