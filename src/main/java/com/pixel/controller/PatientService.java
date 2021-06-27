package com.pixel.controller;

import com.pixel.model.non_entity_projection.ResultDTO;
import com.pixel.model.repository.PatientRepository;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Service
class PatientService {
    private final PatientRepository patientRepository;
    private final EntityManager entityManager;

    PatientService(final PatientRepository patientRepository, final EntityManager entityManager) {
        this.patientRepository = patientRepository;
        this.entityManager = entityManager;
    }

    public List<ResultDTO> findAll(String city, String specialization) {
        return patientRepository.countPatientVisits(city, specialization).orElse(Collections.emptyList());
    }

    // ALTERNATIVE METHOD TO EXTRACT DTOs FROM DATABASE USING ENTITY MANAGER AND RESULT TRANSFORMER
    public List<ResultDTO> getAllNonEntityDTOs(String city, String specialization) {
        List<ResultDTO> nonEntityDTOs;
        nonEntityDTOs = entityManager.createNativeQuery("SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS VISIT_COUNTER FROM PATIENTS " +
                "INNER JOIN VISITS ON PATIENTS.ID = VISITS.PATIENT_ID " +
                "INNER JOIN PRACTITIONERS ON PRACTITIONERS.ID = VISITS.PRACTITIONER_ID " +
                "WHERE CASE " +
                "           WHEN SPECIALIZATION != 'ALL' OR SPECIALIZATION IS NOT NULL THEN" +
                "                 SPECIALIZATION = :specialization " +
                "       END " +
                "AND" +
                "      CASE" +
                "           WHEN CITY != 'ALL' OR CITY IS NOT NULL THEN" +
                "                CITY = :city" +
                "            END " +
                "GROUP BY FIRST_NAME").setParameter("specialization", specialization)
                .setParameter("city", city)
                .unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(ResultDTO.class)).list();
        return nonEntityDTOs;
    }
}
