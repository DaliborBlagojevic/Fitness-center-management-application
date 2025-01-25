package com.example.SitPass.service;

import com.example.SitPass.dto.WorkDayDto;
import com.example.SitPass.model.WorkDay;

import java.util.List;

public interface WorkDayService {

    WorkDay getModel(Long id);

    List<WorkDayDto> findAllByFacility(Long id);
    WorkDayDto create(WorkDayDto workDayDto);
    WorkDayDto update(Long id,WorkDayDto workDayDto);
    void delete(Long id);

}
