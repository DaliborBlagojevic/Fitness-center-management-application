package com.example.SitPass.repository;

import com.example.SitPass.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Override
    Optional<Image> findById(Long id);
}
