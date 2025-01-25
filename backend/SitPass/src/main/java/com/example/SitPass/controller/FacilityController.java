package com.example.SitPass.controller;


import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

import static com.example.SitPass.config.SecurityConfiguration.ROLE_ADMIN;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facilities")
public class FacilityController {

    public final FacilityService facilityService;
    @GetMapping()
    public ResponseEntity<List<FacilityDto>> findAll() {

        return ResponseEntity.ok(facilityService.findAll());
    }


    @GetMapping("/search/city")
    public ResponseEntity<List<FacilityDto>> searchFacilitiesByCity(@RequestParam String city){
        return ResponseEntity.ok(facilityService.searchFacilitiesByCity(city));
    }
    @GetMapping("/search/rate")
    public ResponseEntity<List<FacilityDto>> searchFacilitiesByRating(@RequestParam Double minRate, @RequestParam Double maxRate){
        return ResponseEntity.ok(facilityService.searchFacilitiesByRate(minRate, maxRate));
    }

    @GetMapping("/search/discipline")
    public ResponseEntity<List<FacilityDto>> searchFacilitiesByDiscipline(@RequestParam String discipline){
        return ResponseEntity.ok(facilityService.searchFacilitiesByDiscipline(discipline));
    }

    @GetMapping("/search/day")
    public ResponseEntity<List<FacilityDto>> searchFacilitiesByWorkDay(@RequestParam String time){
        return ResponseEntity.ok(facilityService.searchFacilitiesByDay(time));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> findOneFacility(@PathVariable Long id){
        return ResponseEntity.ok(facilityService.findOneById(id));
    }

    @PostMapping()
    public ResponseEntity<FacilityDto> create(@RequestBody FacilityDto facilityDto) {
        return ResponseEntity.status(CREATED).body(facilityService.create(facilityDto));
    }
    @PutMapping("/{id}")
    public  ResponseEntity<FacilityDto> update(@RequestBody FacilityDto facilityDto,@PathVariable Long id) {
        return  ResponseEntity.ok(facilityService.update(facilityDto, id));
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id) {
        facilityService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
