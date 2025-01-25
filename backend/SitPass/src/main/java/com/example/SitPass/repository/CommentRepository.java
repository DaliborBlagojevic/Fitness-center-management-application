package com.example.SitPass.repository;

import com.example.SitPass.model.Comment;
import com.example.SitPass.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByReviewId(Long id);
}
