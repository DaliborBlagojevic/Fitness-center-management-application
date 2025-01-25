package com.example.SitPass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String path;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    @JsonIgnore
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
