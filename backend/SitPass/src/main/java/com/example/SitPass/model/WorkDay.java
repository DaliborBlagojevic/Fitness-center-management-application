package com.example.SitPass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "work_day")
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate validFrom;

    @Enumerated(EnumType.STRING)
    @Column(name="work_day")
    private DayOfWeek day;

    @Column(name = "start_date")
    private LocalTime startDate;

    @Column(name = "end_date")
    private LocalTime endDate;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    @JsonIgnore
    private Facility facility;

}
