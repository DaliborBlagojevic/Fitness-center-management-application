package com.example.SitPass.service.impl;

import com.example.SitPass.dto.WorkDayDto;
import com.example.SitPass.model.WorkDay;
import com.example.SitPass.repository.WorkDayRepository;
import com.example.SitPass.service.WorkDayService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.WorkDayDto.convertToDto;


@RequiredArgsConstructor
@Service
public class WorkDayServiceImpl implements WorkDayService {


    public final WorkDayRepository workDayRepository;
    @Override
    public WorkDay getModel(Long id) {
        return workDayRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<WorkDayDto> findAllByFacility(Long id) {
        List<WorkDay> workDays= workDayRepository.findAllByFacilityId(id);


        return workDays.stream().map(WorkDayDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public WorkDayDto create(WorkDayDto workDayDto) {
        return convertToDto(workDayRepository.save(workDayDto.convertToModel()));
    }

    @Override
    public WorkDayDto update(Long id, WorkDayDto workDayDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        workDayRepository.delete(getModel(id));
    }
}
