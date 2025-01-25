package com.example.SitPass.service.impl;

import com.example.SitPass.dto.RateDto;
import com.example.SitPass.model.Rate;
import com.example.SitPass.repository.RateRepository;
import com.example.SitPass.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.RateDto.convertToDto;

@RequiredArgsConstructor
@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;


    @Override
    public List<RateDto> findAllByReview(Long id) {

        List<Rate> rates = rateRepository.findAllByReviewId(id);
        return rates.stream().map(RateDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RateDto create(RateDto rateDto) {
        return convertToDto(rateRepository.save(rateDto.convertToModel()));
    }

    @Override
    public Double averageRating(RateDto rateDto) {

        return (rateDto.getEquipment().doubleValue() + rateDto.getHygene().doubleValue() + rateDto.getSpace().doubleValue() + rateDto.getSpace().doubleValue()) / 4;
    }
}
