package com.example.SitPass.repository;

import com.example.SitPass.model.Discipline;
import com.example.SitPass.model.Facility;
import com.example.SitPass.model.WorkDay;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {


    Optional<Facility> findById(Long id);
    Optional<Facility> findByName(String name);


    // Pretraga po gradu (sadrži, bez obzira na velika/mala slova)
    List<Facility> findByCityContainingIgnoreCase(String city);


    @Query(value = "select f.* from Facility f join Discipline d on d.facility_id = f.id join work_day w on f.id = w.facility_id where w.work_day = ?",
            nativeQuery = true)
    List<Facility> searchFacilitiesByDay(String workDay);


    @Query(value = "select f.* from Facility f join Discipline d on d.facility_id = f.id join work_day w on f.id = w.facility_id where d.name = ?",
            nativeQuery = true)
    List<Facility> searchFacilitiesDiscipline(String discipline);

    List<Facility> findByTotalRatingBetween(Double minRate, Double maxRate);
}
