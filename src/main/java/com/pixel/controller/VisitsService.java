package com.pixel.controller;

import com.pixel.model.non_entity_projection.PatientVisits;
import com.pixel.model.non_entity_projection.PractitionerVisits;
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

    public List<PatientVisits> findPatientVisits(List<String> cities, String specialization) {
        String parameterizedPatientVisitsQuery = getParameterizedQuery(cities, specialization);
        return entityManager.createNativeQuery(parameterizedPatientVisitsQuery).getResultList();
    }

    public List<PractitionerVisits> findPractitionerVisits(String specialization) {
        return this.entityManager.createNativeQuery(getPractitionerVisitsQuery())
                .setParameter("specialization", specialization)
                .getResultList();
    }

    private String getParameterizedQuery(final List<String> cities, final String specialization) {
        return String.format(
                "SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS VISITS " +
                        "FROM PATIENTS p " +
                        "INNER JOIN VISITS v ON p.ID = v.PATIENT_ID " +
                        "INNER JOIN PRACTITIONERS pr ON v.PRACTITIONER_ID = pr.ID " +
                        "WHERE (CASE " +
                        "WHEN ('%1$s' = 'ALL') OR ('%1$s' = '') " +
                        "THEN (pr.SPECIALIZATION LIKE '%%') " +
                        "ELSE (pr.SPECIALIZATION = '%1$s') " +
                        "END) " +
                        "AND (CASE " +
                        "WHEN ('%2$s' = 'ALL') OR ('%2$s' = '') " +
                        "THEN (p.CITY LIKE '%%') " +
                        "ELSE (p.CITY IN (%3$s)) " +
                        "END) " +
                        "GROUP BY FIRST_NAME",
                specialization,
                cities.get(0),
                getDynamicIN_ClauseQuery(cities));
    }

    private String getDynamicIN_ClauseQuery(final List<String> cities) {
        StringBuilder citiesQueryBuilder;
        if (cities.size() == 0) {
            citiesQueryBuilder = new StringBuilder("");
        } else if (cities.size() == 1) {
            citiesQueryBuilder = new StringBuilder("'" + cities.get(0) + "'");
        } else {
            citiesQueryBuilder = new StringBuilder("'" + cities.get(0) + "'");
            for (int i = 1; i < cities.size(); i++) {
                citiesQueryBuilder.append(",").append("'").append(cities.get(i)).append("'");
            }
        }
        return citiesQueryBuilder.toString().trim();
    }

    private String getPractitionerVisitsQuery() {
        return "SELECT pr.SPECIALIZATION, COUNT(*) AS VISITS " +
                "FROM PRACTITIONERS pr " +
                "INNER JOIN VISITS v ON pr.ID = v.PRACTITIONER_ID " +
                "WHERE CASE " +
                "WHEN :specialization = 'ALL' OR :specialization = '' " +
                "THEN pr.SPECIALIZATION LIKE '%' " +
                "ELSE pr.SPECIALIZATION = :specialization " +
                "END " +
                "GROUP BY pr.SPECIALIZATION";
    }
}