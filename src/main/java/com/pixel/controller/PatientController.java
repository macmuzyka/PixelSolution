package com.pixel.controller;

import com.pixel.model.non_entity_projection.ResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@RestController
@RequestMapping("/patients")
class PatientController {
    private final PatientService patientService;

    PatientController(final PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/visits/city={city}&practitioner={practitioner}")
    ResponseEntity<List<ResultDTO>> getPatientsVisitsByCityAndSpecialization(
            @PathVariable @RequestParam(defaultValue = "") String city,
            @PathVariable @RequestParam(defaultValue = "") String practitioner) {
        return ResponseEntity.ok().body(patientService.getAllNonEntityDTOs(city, practitioner));
    }
}
