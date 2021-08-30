package com.pixel.model.repository;

import com.pixel.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Integer> {
    List<Practitioner> findAll();
    Practitioner findBySpecialization(String specialization);

    @Override
    boolean existsById(Integer integer);

    @Override
    Optional<Practitioner> findById(Integer integer);
}