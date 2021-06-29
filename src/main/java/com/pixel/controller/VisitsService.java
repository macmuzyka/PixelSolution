package com.pixel.controller;

import com.pixel.model.non_entity_projection.PatientVisitsDTO;
import com.pixel.model.non_entity_projection.PractitionerVisitsDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@SuppressWarnings("unchecked")
@Service
class VisitsService {
    private final EntityManager entityManager;

    VisitsService(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // ALTERNATIVE METHOD TO EXTRACT DTOs FROM DATABASE USING ENTITY MANAGER AND RESULT TRANSFORMER
    public List<PatientVisitsDTO> findPatientVisits(String city, String specialization) {
        return this.entityManager.createNativeQuery("SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS VISITS " +
                "                    FROM PATIENTS p " +
                "                             INNER JOIN VISITS v ON p.ID = v.PATIENT_ID " +
                "                             INNER JOIN PRACTITIONERS pr ON v.PRACTITIONER_ID = pr.ID " +
                "                    WHERE CASE " +
                "                              WHEN :specialization = 'ALL' OR :specialization = '' THEN " +
                "                                      pr.SPECIALIZATION LIKE '%' " +
                "                              ELSE " +
                "                                      pr.SPECIALIZATION = :specialization " +
                "                        END " +
                "                      AND CASE " +
                "                              WHEN :city = 'ALL' OR :city = '' THEN " +
                "                                      p.CITY LIKE '%' " +
                "                              ELSE " +
                "                                      p.CITY = :city " +
                "                        END " +
                "                    GROUP BY FIRST_NAME")
                .setParameter("specialization", specialization)
                .setParameter("city", city)
                .getResultList();
    }

    public List<PractitionerVisitsDTO> findPractitionerVisits(String specialziation) {
        return this.entityManager.createNativeQuery("SELECT pr.SPECIALIZATION, COUNT(*) AS TOTAL_VISITS " +
                "FROM PRACTITIONERS pr " +
                "         INNER JOIN VISITS v ON pr.ID = v.PRACTITIONER_ID " +
                "WHERE CASE " +
                "          WHEN :specialization = 'ALL' OR :specialization = '' " +
                "              THEN pr.SPECIALIZATION LIKE '%' " +
                "          ELSE " +
                "              pr.SPECIALIZATION = :specialization " +
                "          END " +
                "GROUP BY pr.SPECIALIZATION")
                .setParameter("specialization", specialziation)
                .getResultList();
    }
}
