package com.example.SitPass.dto;

import com.example.SitPass.model.Facility;
import com.example.SitPass.model.Menages;
import com.example.SitPass.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenagesDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private User user;
    private Facility facility;


    public static MenagesDto convertToDto(Menages menages) {
        return MenagesDto.builder()
                .id(menages.getId())
                .startDate(menages.getStartDate())
                .endDate(menages.getStartDate())
                .user(menages.getUser())
                .facility(menages.getFacility())
                .build();
    }

    public Menages convertToModel() {
        return Menages.builder()
                .id(getId())
                .startDate(getStartDate())
                .endDate(getEndDate())
                .user(getUser())
                .facility(getFacility())
                .build();
    }

}
