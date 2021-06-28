package com.pixel.model.repository;

import com.pixel.model.Patient;
import com.pixel.model.non_entity_projection.PatientVisitsDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT FIRST_NAME, LAST_NAME, COUNT(*) AS visitCounter " +
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
                    "GROUP BY FIRST_NAME")
    Optional<List<PatientVisitsDTO>> countPatientVisits(@Param("city") String city,
                                                        @Param("specialization") String specialization);
}
