package com.pixel.model.repository;

import com.pixel.model.PatientToPractitioner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientToPractitionerRepository extends CrudRepository<PatientToPractitioner, Integer> {
}
