
package com.example.SitPass.dto;

import com.example.SitPass.model.Rate;
import com.example.SitPass.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RateDto {

    private Long id;
    private Integer equipment;
    private Integer staff;
    private Integer hygene;
    private Integer space;
    private Review review;
    private Double average;

    public static RateDto convertToDto(Rate rate) {
        return RateDto.builder()
                .id(rate.getId())
                .equipment(rate.getEquipment())
                .staff(rate.getStaff())
                .hygene(rate.getHygene())
                .space(rate.getSpace())
                .average(rate.getAverage())
                .review(rate.getReview())
                .build();
    }
    public Rate convertToModel() {
        return Rate.builder()
                .id(getId())
                .equipment(getEquipment())
                .staff(getStaff())
                .hygene(getHygene())
                .space(getSpace())
                .average(getAverage())
                .review(getReview())
                .build();
    }
}
