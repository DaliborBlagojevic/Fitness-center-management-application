package com.example.SitPass.service.impl;

import com.example.SitPass.dto.*;
import com.example.SitPass.exception.BadRequestException;
import com.example.SitPass.model.Comment;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.Rate;
import com.example.SitPass.model.Review;
import com.example.SitPass.repository.ExerciseRepository;
import com.example.SitPass.repository.ReviewRepository;
import com.example.SitPass.repository.UserRepository;
import com.example.SitPass.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.ReviewDto.convertToDto;
import static com.example.SitPass.dto.RateDto.convertToDto;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserService userService;
    private final FacilityService facilityService;
    private final RateService rateService;
    private final CommentService commentService;


    @Override
    public List<ReviewDto> findAllByFacility(Long id) {

        List<Review> reviews = reviewRepository.findAllByFacilityId(id);

        return reviews.stream().map(ReviewDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto create(ReviewDto reviewDto, Long user_id, Long facility_id) {
        Double totalRate = null;

        Facility facility = facilityService.findOneById(facility_id)
                .convertToModel();
        Integer exerciseCount = exerciseRepository.findByUserAndFacility(user_id, facility_id).size();

        if (exerciseCount == 0) {
            throw new BadRequestException("You must have exercise in facility" + facility_id);
        }
            reviewDto.setCreatedAt(LocalDate.now());

            Review review = reviewRepository.save(reviewDto.convertToModel());
            review.setHidden(false);
            review.setUser(userService.findById(user_id).convertToModel());
            review.setFacility(facilityService.findOneById(facility_id).convertToModel());
            review.setExerciseCount(exerciseCount);

            for (Rate rate : review.getRates()) {

                rate.setReview(review);
                rate.setAverage(rateService.averageRating(convertToDto(rate)));
                rateService.create(RateDto.convertToDto(rate));

                totalRate = rate.getAverage();
                facility.setTotalRating(totalRate);
            }

            for (Comment comment : review.getComments()) {
                comment.setReview(review);
                commentService.create(CommentDto.convertToDto(comment));
            }

            facilityService.updateRate(totalRate, facility_id);

            return convertToDto(review);

        }
    }

