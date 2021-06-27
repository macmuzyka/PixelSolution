package com.pixel.model.repository;

import com.pixel.model.Patient;
import com.pixel.model.non_entity_projection.ResultDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS VISIT_COUNTER FROM PATIENTS " +
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
                    "GROUP BY FIRST_NAME")
    Optional<List<ResultDTO>> countPatientVisits(@Param("city") String city,
                                                 @Param("specialization") String specialization);
}
