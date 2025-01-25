package com.example.SitPass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer equipment;

    @Column
    private Integer staff;

    @Column
    private Integer hygene;

    @Column
    private Integer space;

    @Column
    private Double average;

    @ManyToOne
    @JoinColumn(name = "review_id")
    @JsonIgnore
    private Review review;



}
