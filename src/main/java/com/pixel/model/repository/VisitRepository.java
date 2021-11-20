package com.pixel.model.repository;

import com.pixel.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Query(value = "SELECT v.* FROM PATIENTS p INNER JOIN VISITS v ON p.ID = v.PATIENT_ID;", nativeQuery = true)
    List<Visit> findPatientJoin();
}
