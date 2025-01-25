package com.example.SitPass.dto;


import com.example.SitPass.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.reflect.IReflectionWorld;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private LocalDate createdAt;
    private Integer exerciseCount;
    private Boolean hidden;
    private List<Rate> rates;
    private Facility facility;
    private List<Comment> comments;
    private User user;

    public static ReviewDto convertToDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .exerciseCount(review.getExerciseCount())
                .hidden(review.getHidden())
                .rates(review.getRates())
                .facility(review.getFacility())
                .comments(review.getComments())
                .user(review.getUser())
                .build();
    }

    public Review convertToModel() {
        return Review.builder()
                .id(getId())
                .createdAt(getCreatedAt())
                .exerciseCount(getExerciseCount())
                .hidden(getHidden())
                .rates(getRates())
                .facility(getFacility())
                .comments(getComments())
                .user(getUser())
                .build();
    }

}

