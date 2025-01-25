package com.example.SitPass.dto;

import com.example.SitPass.model.Discipline;
import com.example.SitPass.model.Facility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DisciplineDto {

    private Long id;
    private String name;
    private Facility facility;

    public static DisciplineDto convertToDto(Discipline discipline) {
        return DisciplineDto.builder()
                .id(discipline.getId())
                .name(discipline.getName())
                .facility(discipline.getFacility())
                .build();
    }

    public Discipline convertToModel() {
        return Discipline.builder()
                .id(getId())
                .name(getName())
                .facility(getFacility())
                .build();
    }


}
