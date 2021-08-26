package com.pixel.model.repository;

import com.pixel.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);
}
