package com.pixel.controller;

import com.pixel.model.non_entity_projection.PractitionerVisitsDTO;
import com.pixel.model.repository.PractitionerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 28.06.2021
 */

@Service
class PractitionerService {

    private final PractitionerRepository practitionerRepository;

    PractitionerService(final PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    public List<PractitionerVisitsDTO> findAllPractitionersVisitsDTOs(String specialization) {
        return practitionerRepository.findPractitionerVisits(specialization).orElse(Collections.emptyList());
    }


}
