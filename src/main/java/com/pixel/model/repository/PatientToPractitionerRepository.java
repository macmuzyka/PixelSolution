package com.pixel.model.repository;

import com.pixel.model.PatientToPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientToPractitionerRepository extends JpaRepository<PatientToPractitioner, Integer> {
}
