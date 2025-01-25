package com.example.SitPass.repository;

import com.example.SitPass.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "select * from Exercise where user_id = ?", nativeQuery = true)
    List<Exercise> findByUser(Long id);

    @Query(value = "select * from Exercise where user_id = ? and facility_id = ?", nativeQuery = true)
    List<Exercise> findByUserAndFacility(Long user_id, Long facility_id);

}
