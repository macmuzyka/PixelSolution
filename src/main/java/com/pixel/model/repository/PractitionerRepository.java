package com.pixel.model.repository;

import com.pixel.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Integer> {
    List<Practitioner> findAll();
    Practitioner findBySpecialization(String specialization);
}