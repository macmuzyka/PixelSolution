package com.pixel.model.repository;

import com.pixel.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);

    @Override
    boolean existsById(Integer integer);

    @Override
    Optional<Patient> findById(Integer integer);

    List<Patient> findPatientsByCreatedAtBefore(LocalDateTime timestamp);

    List<Patient> findPatientsByCreatedAtBetween(LocalDate from, LocalDate upTo);
}
