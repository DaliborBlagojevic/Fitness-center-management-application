package com.example.SitPass.service.impl;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.dto.WorkDayDto;
import com.example.SitPass.exception.BadRequestException;
import com.example.SitPass.exception.NotFoundException;
import com.example.SitPass.model.*;
import com.example.SitPass.repository.DisciplineRepository;
import com.example.SitPass.repository.FacilityRepository;
import com.example.SitPass.service.DisciplineService;
import com.example.SitPass.service.FacilityService;
import com.example.SitPass.service.ImageService;
import com.example.SitPass.service.WorkDayService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.SitPass.dto.FacilityDto.convertToDto;

@RequiredArgsConstructor
@Service
public class FacilityServiceImpl implements FacilityService {

    public final FacilityRepository facilityRepository;
    public final DisciplineRepository disciplineRepository;
    public final DisciplineService disciplineService;
    public final ImageService imageService;
    public final WorkDayService workDayService;


    @Override
    public Facility getModel(Long id) {

        return facilityRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Facility with id %s not found.", id)));
    }
    @Override
    public List<FacilityDto> findAll() {

        List<Facility> facilities = facilityRepository.findAll();


        return facilities.stream().map(FacilityDto::convertToDto).collect(Collectors.toList());
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FacilityDto> searchFacilities(String city, Double minRate, Double maxRate, String discipline, String workDay) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Facility> cq = cb.createQuery(Facility.class);
        Root<Facility> facility = cq.from(Facility.class);

        List<Predicate> predicates = new ArrayList<>();

        if (city != null && !city.isEmpty()) {
            predicates.add(cb.equal(facility.get("city"), city));
        }
        if (minRate != null && maxRate != null) {
            predicates.add(cb.between(facility.get("totalRating"), minRate, maxRate));
        }
        if (discipline != null && !discipline.isEmpty()) {
            // Joins should be handled here
            // Example: predicates.add(cb.equal(facility.join("disciplines").get("name"), discipline));
        }
        if (workDay != null && !workDay.isEmpty()) {
            // Joins should be handled here
            // Example: predicates.add(cb.equal(facility.join("workDays").get("workDay"), workDay));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        List<Facility> facilities = entityManager.createQuery(cq).getResultList();

        return facilities.stream()
                .map(FacilityDto::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<FacilityDto> searchFacilitiesByCity(String city) {


        return facilityRepository.findByCityContainingIgnoreCase(city)
                .stream()
                .map(FacilityDto::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<FacilityDto> searchFacilitiesByRate(Double minRate, Double maxRate) {


        return facilityRepository.findByTotalRatingBetween(minRate, maxRate)
                .stream()
                .map(FacilityDto::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<FacilityDto> searchFacilitiesByDiscipline(String discipline) {


        return facilityRepository.searchFacilitiesDiscipline(discipline)
                .stream()
                .map(FacilityDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FacilityDto> searchFacilitiesByDay(String workDay) {


        return facilityRepository.searchFacilitiesByDay(workDay)
                .stream()
                .map(FacilityDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacilityDto findOneById(Long id) {

        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found.", id)));
        return convertToDto(facility);
    }
    @Override
    public FacilityDto create(FacilityDto facilityDto) {

        facilityDto.setCreatedAt(LocalDate.now());
        Facility facility = facilityRepository.save(facilityDto.convertToModel());
        facility.setActive(false);
        facility.setDeleted(false);

        for (Discipline discipline: facility.getDisciplines()) {
            discipline.setFacility(facility);
            disciplineService.create(DisciplineDto.convertToDto(discipline));
        }


        for (WorkDay workDay: facility.getWorkDays()) {
            workDay.setFacility(facility);
            workDay.setValidFrom(LocalDate.now());
            workDayService.create(WorkDayDto.convertToDto(workDay));
        }


        return convertToDto(facility);
    }

    @Override
    public DisciplineDto editDiscipline(DisciplineDto disciplineDto, Long id) {

        Discipline existingDiscipline = disciplineRepository.findOneByFacilityId(id);


        existingDiscipline.setName(disciplineDto.getName());


        Discipline savedDiscipline = disciplineRepository.save(existingDiscipline);
        return DisciplineDto.convertToDto(savedDiscipline);
    }

    @Override
    public FacilityDto updateRate(Double newValue, Long id) {
        Facility facility = facilityRepository.findById(id).orElseThrow();
        if(facility.getTotalRating() != null) {

            facility.setTotalRating((facility.getTotalRating() + newValue) / 2);
        }
        else {
            facility.setTotalRating(newValue);
        }
        Facility saveFacility = facilityRepository.save(facility);
        return convertToDto(facility);
    }

    @Override
    public FacilityDto update(FacilityDto facilityDto, Long id) {
        Facility facilityExisting = facilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found.")));



        facilityExisting.setDescription(facilityDto.getDescription());
        facilityExisting.setCity(facilityDto.getCity());
        facilityExisting.setAddress(facilityDto.getAddress());
        facilityExisting.setName(facilityDto.getName());

        for (Discipline discipline: facilityExisting.getDisciplines()) {

            disciplineService.update(id, DisciplineDto.convertToDto(discipline));
        }


        Facility facilitySaved = facilityRepository.save(facilityExisting);
        return convertToDto(facilitySaved);
    }
    @Override
    public void delete(Long id) {
        Facility facility = getModel(id);
        facilityRepository.deleteById(id);
    }
}
