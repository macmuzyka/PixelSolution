package com.pixel.model.repository;

import com.pixel.model.Practitioner;
import com.pixel.model.non_entity_projection.PractitionerVisitsDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PractitionerRepository extends CrudRepository<Practitioner, Integer> {
    @Query(nativeQuery = true, value = "SELECT pr.SPECIALIZATION, COUNT(*) AS TOTAL_VISITS " +
            "FROM PRACTITIONERS pr " +
            "         INNER JOIN VISITS v ON pr.ID = v.PRACTITIONER_ID " +
            "WHERE CASE " +
            "          WHEN :specialization = 'ALL' OR pr.SPECIALIZATION IS NULL " +
            "              THEN pr.SPECIALIZATION LIKE '%' " +
            "          ELSE " +
            "              pr.SPECIALIZATION = :specialization " +
            "          END " +
            "GROUP BY pr.SPECIALIZATION")
    Optional<List<PractitionerVisitsDTO>> findPractitionerVisits(@Param("specialization") String specialization);
}
