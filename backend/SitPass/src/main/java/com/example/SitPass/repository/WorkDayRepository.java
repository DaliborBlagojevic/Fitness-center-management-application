package com.example.SitPass.repository;

import com.example.SitPass.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long>{

    @Override
    Optional<WorkDay> findById(Long aLong);

    List<WorkDay> findAllByFacilityId(Long id);
}
