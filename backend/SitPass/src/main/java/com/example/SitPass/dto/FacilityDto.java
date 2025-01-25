package com.example.SitPass.dto;

import com.example.SitPass.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private String address;
    private String city;
    private Double totalRating;
    private Boolean active;
    private Boolean deleted;
    private List<Exercise> exercises;
    private List<Review> reviews;
    private List<WorkDay> workDays;
    private List<Image> images;
    private List<Discipline> disciplines;

    public static FacilityDto convertToDto(Facility facility) {
        return FacilityDto.builder()
                .id(facility.getId())
                .name(facility.getName())
                .description(facility.getDescription())
                .createdAt(facility.getCreatedAt())
                .address(facility.getAddress())
                .city(facility.getCity())
                .totalRating(facility.getTotalRating())
                .active(facility.getActive())
                .workDays(facility.getWorkDays())
                .images(facility.getImages())
                .disciplines(facility.getDisciplines())
                .build();
    }

    public Facility convertToModel() {
        return Facility.builder()
                .id(getId())
                .name(getName())
                .description(getDescription())
                .createdAt(getCreatedAt())
                .address(getAddress())
                .city(getCity())
                .totalRating(getTotalRating())
                .active(getActive())
                .workDays(getWorkDays())
                .images(getImages())
                .disciplines(getDisciplines())
                .build();
    }
}
