package com.example.SitPass.repository;

import com.example.SitPass.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {


    List<Discipline> findAllByFacilityId(Long id);

    Discipline findOneByFacilityId(Long id);

    Discipline findOneById(Long id);
}
