package com.example.SitPass.controller;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.service.DisciplineService;
import com.example.SitPass.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disciplines")
public class DisciplineController {

    public final DisciplineService disciplineService;
    public final FacilityService facilityService;

    @GetMapping("/{id}")
    public ResponseEntity<List<DisciplineDto>> findAllById(@PathVariable Long id) {

        return ResponseEntity.ok(disciplineService.findByFacility(id));
    }

    @GetMapping("")
    public ResponseEntity<List<DisciplineDto>> findAll() {

        return ResponseEntity.ok(disciplineService.findAll());
    }

    @PostMapping()
    public ResponseEntity<DisciplineDto> create(@RequestBody DisciplineDto disciplineDto) {
        return ResponseEntity.ok(disciplineService.create(disciplineDto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DisciplineDto> updateDiscipline(@RequestBody DisciplineDto disciplineDto, @PathVariable Long id) {
        return  ResponseEntity.ok(disciplineService.update(id, disciplineDto));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        disciplineService.delete(id);
    }


}
