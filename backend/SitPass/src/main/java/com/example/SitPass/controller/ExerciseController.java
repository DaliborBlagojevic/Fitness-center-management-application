package com.example.SitPass.controller;

import com.example.SitPass.dto.ExerciseDto;
import com.example.SitPass.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    public  ResponseEntity<List<ExerciseDto>> viewAllByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(exerciseService.findByUser(id));
    }

    @PostMapping("/{facilityId}/{email}")
    public ResponseEntity<ExerciseDto> createExercise(@Valid @RequestBody ExerciseDto exerciseDto, @PathVariable Long facilityId, @PathVariable String email) {
        return ResponseEntity.status(CREATED).body(exerciseService.create(exerciseDto, facilityId, email));
    }

}
