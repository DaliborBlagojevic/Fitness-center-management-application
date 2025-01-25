package com.example.SitPass.service.impl;

import com.example.SitPass.dto.CommentDto;
import com.example.SitPass.model.Comment;
import com.example.SitPass.repository.CommentRepository;
import com.example.SitPass.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> findAllByReview(Long id) {
        List<Comment> comments = commentRepository.findAllByReviewId(id);

        return comments.stream().map(CommentDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto create(CommentDto commentDto) {

        commentDto.setCreatedAt(LocalDate.now());
        return CommentDto.convertToDto(commentRepository.save(commentDto.convertToModel()));
    }
}
