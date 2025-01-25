package com.example.SitPass.service;

import com.example.SitPass.dto.RateDto;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.Rate;

import java.util.List;

public interface RateService {

    List<RateDto> findAllByReview(Long id);
    RateDto create(RateDto rateDto);
    Double averageRating(RateDto rateDto);

}
