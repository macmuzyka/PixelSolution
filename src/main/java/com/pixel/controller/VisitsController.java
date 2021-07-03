package com.pixel.controller;

import com.pixel.model.non_entity_projection.PatientVisits;
import com.pixel.model.non_entity_projection.PractitionerVisits;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@RestController
@RequestMapping("/visits")
class VisitsController {
    private final VisitsService visitsService;

    VisitsController(final VisitsService visitsService) {
        this.visitsService = visitsService;

    }

    @GetMapping(value = "/patients/city={cities}&practitioner={practitioner}")
    ResponseEntity<List<PatientVisits>> getPatientsVisitsByCityAndSpecialization(
            @PathVariable @NotNull List<String> cities,
            @PathVariable @NotNull String practitioner) {
        return ResponseEntity.ok().body(visitsService.findPatientVisits(cities, practitioner));
    }

    @GetMapping("/practitioner={practitioner}")
    ResponseEntity<List<PractitionerVisits>> getPractitionerVisits(
            @PathVariable @NotNull String practitioner) {
        return ResponseEntity.ok().body((visitsService.findPractitionerVisits(practitioner)));
    }
}
