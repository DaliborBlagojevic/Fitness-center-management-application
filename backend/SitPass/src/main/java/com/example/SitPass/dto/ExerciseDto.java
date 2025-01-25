package com.example.SitPass.dto;

import com.example.SitPass.model.Exercise;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {

    private Long id;
    private LocalDateTime fromDate;
    private LocalDateTime untilDate;
    private Facility facility;
    private User user;

    public static ExerciseDto convertToDto(Exercise exercise) {
        return  ExerciseDto.builder()
                .id(exercise.getId())
                .fromDate(exercise.getFromDate())
                .untilDate(exercise.getUntilDate())
                .facility(exercise.getFacility())
                .user(exercise.getUser())
                .build();
    }

    public  Exercise convertToModel() {
        return Exercise.builder()
                .id(getId())
                .fromDate(getFromDate())
                .untilDate(getUntilDate())
                .facility(getFacility())
                .user(getUser())
                .build();
    }

}
