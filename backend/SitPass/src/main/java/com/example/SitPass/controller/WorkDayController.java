package com.example.SitPass.controller;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.dto.WorkDayDto;
import com.example.SitPass.service.DisciplineService;
import com.example.SitPass.service.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/days")
public class WorkDayController {

    public final WorkDayService workDayService;

    @GetMapping("/{id}")
    public ResponseEntity<List<WorkDayDto>> findAll(@PathVariable Long id) {

        return ResponseEntity.ok(workDayService.findAllByFacility(id));
    }

    @PostMapping()
    public ResponseEntity<WorkDayDto> create(@RequestBody WorkDayDto workDayDto) {
        return ResponseEntity.status(CREATED).body(workDayService.create(workDayDto));
    }



    @DeleteMapping("/{id}")
    void delete (@PathVariable Long id) {
        workDayService.delete(id);
    }
}
