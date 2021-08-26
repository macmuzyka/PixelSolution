package com.pixel.controller;

import com.pixel.model.Practitioner;
import com.pixel.model.repository.PractitionerRepository;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 24.08.2021
 */

@RestController
@RequestMapping("/practitioners")
class PractitionerController {

    private final PractitionerRepository practitionerRepository;

    PractitionerController(final PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Practitioner>> getAllPractitioners() {
        return ResponseEntity.ok(practitionerRepository.findAll());
    }

    @GetMapping("/specialization={specialization}")
    ResponseEntity<Practitioner> getPractitioner(@PathVariable @NotNull String specialization) {
        return ResponseEntity.ok().body(practitionerRepository.findBySpecialization(specialization));
    }

    @PostMapping("/add")
    ResponseEntity<?> addNewPractitioner(@RequestBody @Valid Practitioner newPractitioner) {
        Practitioner repositoryPractitioner = practitionerRepository.save(newPractitioner);
        return ResponseEntity.created(URI.create("/" + repositoryPractitioner.getId())).body(repositoryPractitioner);
    }
}
