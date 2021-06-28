package com.pixel.controller;

import com.pixel.model.non_entity_projection.PatientVisitsDTO;
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

    public List<PatientVisitsDTO> findAllPatientVisitsDTOs(String city, String specialization) {
        return patientRepository.countPatientVisits(city, specialization).orElse(Collections.emptyList());
    }

    // ALTERNATIVE METHOD TO EXTRACT DTOs FROM DATABASE USING ENTITY MANAGER AND RESULT TRANSFORMER
    public List<PatientVisitsDTO> findAllDTOs(String city, String specialization) {
        List<PatientVisitsDTO> nonEntityDTOs;
        nonEntityDTOs = entityManager.createNativeQuery("SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS visitCounter " +
                "FROM PATIENTS p " +
                "         INNER JOIN VISITS v ON p.ID = v.PATIENT_ID " +
                "         INNER JOIN PRACTITIONERS pr ON v.PRACTITIONER_ID = pr.ID " +
                "WHERE CASE " +
                "          WHEN :specialization = 'ALL' OR :specialization IS NULL THEN " +
                "                  pr.SPECIALIZATION LIKE '%' " +
                "          ELSE " +
                "                  pr.SPECIALIZATION = :specialization " +
                "    END " +
                "  AND CASE " +
                "          WHEN :city = 'ALL' OR p.CITY IS NULL THEN " +
                "                  p.CITY LIKE '%' " +
                "          ELSE " +
                "                  p.CITY = :city " +
                "    END " +
                "GROUP BY FIRST_NAME").setParameter("specialization", specialization)
                .setParameter("city", city)
                .unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.aliasToBean(PatientVisitsDTO.class)).getResultList();
        return nonEntityDTOs;
    }
}
