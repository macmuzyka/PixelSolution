package com.pixel.service;

import com.pixel.model.non_entity_projection.PatientVisits;
import com.pixel.model.non_entity_projection.PractitionerVisits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@SuppressWarnings("unchecked")
@Service
public class VisitsService {
    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(VisitsService.class);

    VisitsService(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PatientVisits> findPatientVisitsWithCityAndPractitionerParameters(List<String> cities, String specialization) {
        emptyListValidation(cities);

        String parameterizedPatientVisitsQuery = QueryService.provideParameterizedQuery(cities, specialization);
        logger.info("Exposing patient visits.");
        return entityManager.createNativeQuery(parameterizedPatientVisitsQuery).getResultList();
    }

    public List<PractitionerVisits> findPractitionerVisits(String specialization) {
        logger.info("Exposing practitioner visits.");
        String practitionerVisitsQuery = QueryService.providePractitionerVisitsQuery();
        return this.entityManager.createNativeQuery(practitionerVisitsQuery)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    public List<PatientVisits> findAllPatientsVisits() {
        String allPatientsVisitsQuery = QueryService.provideAllPatientVisitsQuery();
        return entityManager.createNativeQuery(allPatientsVisitsQuery).getResultList();
    }

    public List<PatientVisits> findWildcardPatientVisits(String firstName, String lastName) {
        String patientVisitsQuery = QueryService.provideWildcardCaseSensitivePatientVisitsQuery(firstName, lastName);
        logger.info("Exposing wildcard patients visits");
        return entityManager.createNativeQuery(patientVisitsQuery).getResultList();
    }

    private void emptyListValidation(final List<String> cities) {
        if (cities.isEmpty()) {
            cities.add("");
        }
    }
}