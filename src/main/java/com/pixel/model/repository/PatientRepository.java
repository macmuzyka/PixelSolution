package com.pixel.model.repository;

import com.pixel.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);

    @Override
    boolean existsById(Integer integer);

    @Override
    Optional<Patient> findById(Integer integer);
}
