package com.example.SitPass.service;

import com.example.SitPass.dto.ExerciseDto;
import com.example.SitPass.model.Facility;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> viewAll();

    List<ExerciseDto> findByUser(Long id);
    ExerciseDto create(ExerciseDto exerciseDto, Long facilityId, String email);

    void validateExerciseTime(ExerciseDto exerciseDto, Facility facility);

}
