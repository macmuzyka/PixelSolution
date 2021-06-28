package com.pixel.controller;

import com.pixel.model.non_entity_projection.PatientVisitsDTO;
import com.pixel.model.non_entity_projection.PractitionerVisitsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@RestController
@RequestMapping("/visits")
class MainController {
    private final PatientService patientService;
    private final PractitionerService practitionerService;

    MainController(final PatientService patientService, final PractitionerService practitionerService) {
        this.patientService = patientService;
        this.practitionerService = practitionerService;
    }

    @GetMapping("/patients/city={city}&practitioner={practitioner}")
    ResponseEntity<List<PatientVisitsDTO>> getPatientsVisitsByCityAndSpecialization(
            @PathVariable String city,
            @PathVariable String practitioner) {
        return ResponseEntity.ok().body(patientService.findAllPatientVisitsDTOs(city, practitioner));
    }

    @GetMapping("/practitioner={practitioner}")
    ResponseEntity<List<PractitionerVisitsDTO>> getPractitionerVisits(
            @PathVariable String practitioner) {
        return ResponseEntity.ok().body((practitionerService.findAllPractitionersVisitsDTOs(practitioner)));
    }

}
