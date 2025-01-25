package com.example.SitPass.service.impl;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.dto.FacilityDto;
import com.example.SitPass.dto.WorkDayDto;
import com.example.SitPass.model.Discipline;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.WorkDay;
import com.example.SitPass.repository.DisciplineRepository;
import com.example.SitPass.repository.FacilityRepository;
import com.example.SitPass.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.DisciplineDto.convertToDto;

@RequiredArgsConstructor
@Service
public class DisciplineServiceImpl implements DisciplineService {

    public final DisciplineRepository disciplineRepository;
    public final FacilityRepository facilityRepository;


    @Override
    public List<DisciplineDto> findAll() {

        List<Discipline> disciplines = disciplineRepository.findAll();

        return disciplines.stream().map(DisciplineDto::convertToDto).collect(Collectors.toList());
    }


    @Override
    public List<DisciplineDto> findByFacility(Long id) {

        List<Discipline> disciplines = disciplineRepository.findAllByFacilityId(id);

        return disciplines.stream().map(DisciplineDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Discipline getModel(Long id) {
        return disciplineRepository.findById(id).orElseThrow();
    }

    @Override
    public DisciplineDto create(DisciplineDto disciplineDto) {
        return convertToDto(disciplineRepository.save(disciplineDto.convertToModel()));
    }

    @Override
    public DisciplineDto update(Long id, DisciplineDto disciplineDto) {
        Discipline discipline = disciplineRepository.findOneById(id);
        discipline.setName(discipline.getName());
        return convertToDto(discipline);
    }


    @Override
    public void delete(Long id) {
        disciplineRepository.delete(getModel(id));
    }
}
