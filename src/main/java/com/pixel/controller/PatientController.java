package com.pixel.controller;

import com.pixel.model.Patient;
import com.pixel.model.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 24.08.2021
 * TODO: add logger warning and infos
 */

@RestController
@RequestMapping("/patients")
class PatientController {

    private final PatientRepository patientRepository;

    PatientController(final PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/name={firstName}&lastname={lastName}")
    ResponseEntity<Patient> getDesiredPatient(@PathVariable final String firstName, @PathVariable final String lastName) {
        return ResponseEntity.ok().body(patientRepository.findPatientByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping("/add")
    ResponseEntity<?> addNewPatient(@RequestBody @Valid Patient newPatient) {
        Patient repositoryPatient = patientRepository.save(newPatient);
        return ResponseEntity.created(URI.create("/" + repositoryPatient.getId())).body(repositoryPatient);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable int id, @RequestBody Patient newPatient) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        patientRepository.findById(id)
                .ifPresent(patient -> patient.updateFrom(newPatient));
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatientCity(@PathVariable int id, @RequestBody String newCity) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        patientRepository.findById(id)
                .ifPresent(patient -> patient.setCity(newCity));
        return ResponseEntity.noContent().build();
    }
}
