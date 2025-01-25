package com.example.SitPass.service;

import com.example.SitPass.dto.DisciplineDto;
import com.example.SitPass.model.Discipline;

import java.util.List;

public interface DisciplineService {

    Discipline getModel(Long id);

    List<DisciplineDto> findAll();

    List<DisciplineDto> findByFacility(Long id);
    DisciplineDto create(DisciplineDto disciplineDto);
    DisciplineDto update(Long id, DisciplineDto disciplineDto);
    void delete(Long id);

}
