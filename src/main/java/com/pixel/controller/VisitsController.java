package com.pixel.controller;

import com.pixel.model.PatientToPractitioner;
import com.pixel.model.Visit;
import com.pixel.model.non_entity_projection.PatientVisits;
import com.pixel.model.non_entity_projection.PractitionerVisits;
import com.pixel.model.repository.PatientToPractitionerRepository;
import com.pixel.model.repository.VisitRepository;
import com.pixel.service.VisitsService;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 * TODO: finish adding new visit endpoint
 */

@RestController
@RequestMapping("/visits")
class VisitsController {
    private final VisitsService visitsService;
    private final VisitRepository visitRepository;
    private final PatientToPractitionerRepository p2pRepository;
    private static final Logger logger = LoggerFactory.getLogger(VisitsController.class);

    VisitsController(final VisitsService visitsService,
                     final VisitRepository visitRepository,
                     final PatientToPractitionerRepository p2pRepository) {
        this.visitsService = visitsService;
        this.visitRepository = visitRepository;
        this.p2pRepository = p2pRepository;
    }

    @GetMapping("/patients/all")
    ResponseEntity<List<PatientVisits>> getAllPatientsVisits() {
        return ResponseEntity.ok().body(visitsService.findAllPatientsVisits());
    }

    @GetMapping("/patients/name={firstName}&lastname={lastName}")
    ResponseEntity<List<PatientVisits>> getPatientVisitsCount(@PathVariable String firstName,
                                                              @PathVariable String lastName) {
        return ResponseEntity.ok().body(visitsService.findWildcardPatientVisits(firstName, lastName));
    }

    @GetMapping("/patients/city={cities}&practitioner={practitioner}")
    ResponseEntity<List<PatientVisits>> getPatientsVisitsByCityAndSpecialization(
            @PathVariable @NotNull List<String> cities,
            @PathVariable @NotNull String practitioner) {
        return ResponseEntity.ok().body(visitsService.findPatientVisitsWithCityAndPractitionerParameters(cities, practitioner));
    }

    @GetMapping("/practitioner={practitioner}")
    ResponseEntity<List<PractitionerVisits>> getPractitionerVisits(
            @PathVariable @NotNull String practitioner) {
        return ResponseEntity.ok().body((visitsService.findPractitionerVisits(practitioner)));
    }

    @PostMapping("/add")
    ResponseEntity<?> addNewVisit(@RequestBody PatientToPractitioner p2p) {
        Visit newVisit = new Visit(p2p.getPractitioner_id(), p2p.getPatient_id());

        Visit repositoryVisit = visitRepository.save(newVisit);
        p2pRepository.save(p2p);

        return ResponseEntity.created(URI.create("/" + repositoryVisit.getId())).body(repositoryVisit);
    }
}
