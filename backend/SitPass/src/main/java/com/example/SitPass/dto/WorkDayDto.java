package com.example.SitPass.dto;

import com.example.SitPass.model.Facility;
import com.example.SitPass.model.WorkDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
@Data
public class WorkDayDto {

    private Long id;
    private LocalDate validForm;
    private DayOfWeek day;
    private LocalTime startDate;
    private LocalTime endDate;
    private Facility facility;

    public static WorkDayDto convertToDto(WorkDay workDay) {
        return WorkDayDto.builder()
                .id(workDay.getId())
                .validForm(workDay.getValidFrom())
                .day(workDay.getDay())
                .startDate(workDay.getStartDate())
                .endDate(workDay.getEndDate())
                .facility(workDay.getFacility())
                .build();
    }

    public WorkDay convertToModel() {
        return WorkDay.builder()
                .id(getId())
                .validFrom(getValidForm())
                .day(getDay())
                .startDate(getStartDate())
                .endDate(getEndDate())
                .facility(getFacility())
                .build();
    }

}
