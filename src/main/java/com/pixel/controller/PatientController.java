package com.pixel.controller;

import com.pixel.model.Patient;
import com.pixel.model.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @GetMapping("/created/earlier/than={timestamp}")
    ResponseEntity<List<Patient>> getPatientsCreatedEarlierThanGivenTimestamp(@PathVariable String timestamp) {
        String[] dateTimeArray = timestamp.replace("'", "").split("T");
        String[] date = dateTimeArray[0].split("-");
        String[] time = dateTimeArray[1].split(":");
        LocalDate d = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        LocalTime t = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[0]));

        return ResponseEntity.ok(patientRepository.findPatientsByCreatedAtBefore(LocalDateTime.of(d, t)));
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

    @GetMapping("/between")
    public ResponseEntity<List<Patient>> getPatientsCreatedBetween(@RequestParam String from,
                                                                   @RequestParam String upTo) {
        return ResponseEntity.ok(patientRepository.findPatientsByCreatedAtBetween(from, upTo));
    }
}

