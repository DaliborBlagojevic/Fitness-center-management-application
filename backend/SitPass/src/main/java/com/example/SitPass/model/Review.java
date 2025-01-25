package com.example.SitPass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate createdAt;

    @Column
    private Integer exerciseCount;

    @Column
    private Boolean hidden;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    @JsonIgnore
    private Facility facility;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
