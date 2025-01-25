package com.example.SitPass.dto;

import com.example.SitPass.model.Comment;
import com.example.SitPass.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String text;
    private LocalDate createdAt;
    private Review review;


    public static CommentDto convertToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .review(comment.getReview())
                .build();
    }

    public Comment convertToModel() {
        return Comment.builder()
                .id(getId())
                .text(getText())
                .createdAt(getCreatedAt())
                .review(getReview())
                .build();
    }

    
}
