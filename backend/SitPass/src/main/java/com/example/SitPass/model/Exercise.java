package com.example.SitPass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime fromDate;

    @Column
    private LocalDateTime untilDate;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    @JsonIgnore
    private Facility facility;

    @ManyToOne

    private User user;
}
