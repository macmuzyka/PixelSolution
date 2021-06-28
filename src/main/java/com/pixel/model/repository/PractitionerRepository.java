package com.pixel.model.repository;

import com.pixel.model.Practitioner;
import com.pixel.model.non_entity_projection.PractitionerVisitsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Integer> {
    @Query(nativeQuery = true, value = "SELECT pr.SPECIALIZATION, COUNT(*) AS TOTAL_VISITS " +
            "FROM PRACTITIONERS pr " +
            "         INNER JOIN VISITS v ON pr.ID = v.PRACTITIONER_ID " +
            "WHERE CASE " +
            "          WHEN :specialization = 'ALL' OR :specialization IS NULL " +
            "              THEN pr.SPECIALIZATION LIKE '%' " +
            "          ELSE " +
            "              pr.SPECIALIZATION = :specialization " +
            "          END " +
            "GROUP BY pr.SPECIALIZATION")
    Optional<List<PractitionerVisitsDTO>> findPractitionerVisits(@Param("specialization") String specialization);


    /*@Query(value = "SELECT new com.pixel.model.non_entity_projection.PractitionerVisitsDTO(pr.specialization, COUNT(v.id)) FROM " +
            "Practitioner pr INNER JOIN Visit v ON pr.id = v.practitioner_id " +
            "WHERE (CASE WHEN :specialization = 'ALL' OR :specialization IS NULL " +
            "THEN (pr.specialization LIKE '%') ELSE " +
            "(pr.specialization = :specialization) END) = true " +
            "GROUP BY pr.specialization")
    Optional<List<PractitionerVisitsDTO>> findSomething(@Param("specialization") String specialization);*/
}