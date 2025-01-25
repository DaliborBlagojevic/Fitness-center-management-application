package com.example.SitPass.service;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.model.Facility;

import java.util.List;
import java.util.Map;

public interface FacilityService {


    List<FacilityDto> findAll();



    List<FacilityDto> searchFacilitiesByRate(Double minRate, Double maxRate);

    List<FacilityDto> searchFacilitiesByDiscipline(String discipline);

    List<FacilityDto> searchFacilitiesByDay(String workDay);

    FacilityDto findOneById(Long id);
    FacilityDto create(FacilityDto facilityDto);


    DisciplineDto editDiscipline(DisciplineDto disciplineDto, Long id);

    FacilityDto updateRate(Double newValue, Long id);

    FacilityDto update(FacilityDto facilityDto, Long id);
    void delete(Long id);
    Facility getModel(Long id);


    List<FacilityDto> searchFacilities(String city, Double minRate, Double maxRate, String discipline, String workDay);

    List<FacilityDto> searchFacilitiesByCity(String searchParam);


}
