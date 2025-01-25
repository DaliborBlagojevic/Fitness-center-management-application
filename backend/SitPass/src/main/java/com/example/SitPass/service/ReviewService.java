package com.example.SitPass.service;

import com.example.SitPass.dto.ExerciseDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.dto.ReviewDto;
import com.example.SitPass.model.Facility;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAllByFacility(Long id);
    ReviewDto create(ReviewDto reviewDto, Long user_id, Long facility_id);


}
