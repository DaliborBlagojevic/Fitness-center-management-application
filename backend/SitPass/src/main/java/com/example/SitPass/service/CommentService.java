package com.example.SitPass.service;

import com.example.SitPass.dto.CommentDto;
import com.example.SitPass.dto.RateDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findAllByReview(Long id);
    CommentDto create(CommentDto commentDto);
}
