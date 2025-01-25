package com.example.SitPass.controller;

import com.example.SitPass.dto.CommentDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.dto.RateDto;
import com.example.SitPass.dto.ReviewDto;
import com.example.SitPass.model.Comment;
import com.example.SitPass.service.CommentService;
import com.example.SitPass.service.RateService;
import com.example.SitPass.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final RateService rateService;
    private final ReviewService reviewService;
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDto>> findAllByFacility(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findAllByFacility(id));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<List<CommentDto>> findCommentsByFacility(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findAllByReview(id));
    }

    @GetMapping("/rates/{id}")
    public ResponseEntity<List<RateDto>> findRatesByFacility(@PathVariable Long id) {
        return ResponseEntity.ok(rateService.findAllByReview(id));
    }

    @PostMapping("/{user_id}/{facility_id}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable Long user_id,@PathVariable Long facility_id) {
        return ResponseEntity.status(CREATED).body(reviewService.create(reviewDto, user_id, facility_id));
    }

}
