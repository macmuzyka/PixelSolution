package com.pixel.model.repository;

import com.pixel.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Query(value = "SELECT v.* FROM PATIENTS p INNER JOIN VISITS v ON p.ID = v.PATIENT_ID;", nativeQuery = true)
    List<Visit> findPatientJoin();

    @Query(value = "SELECT * FROM VISITS V INNER JOIN PATIENTS P on V.PATIENT_ID = P.ID " +
            "WHERE P.CREATED_AT >= (:beginning) " +
            "AND P.CREATED_AT <= (:end);",
            nativeQuery = true)
    List<Visit> findElseVisits(@Param("beginning") String beginning,
                               @Param("end") String end);

    @Query(value = "SELECT COUNT(*) FROM VISITS V INNER JOIN PATIENTS P on V.PATIENT_ID = P.ID " +
            "WHERE P.CREATED_AT >= (:beginning) " +
            "AND P.CREATED_AT <= (:end);",
            nativeQuery = true)
    Integer findElseVisitsCount(@Param("beginning") String beginning,
                               @Param("end") String end);

    @Query(value = "SELECT * FROM VISITS V WHERE V.PATIENT_ID NOT IN( " +
            "SELECT P.ID FROM PATIENTS P WHERE CREATED_AT >= (:beginning) " +
            "AND CREATED_AT <= (:end)" +
            ");"
            , nativeQuery = true)
    List<Visit> findVisitsWithComplexQuery(@Param("beginning") String beginning,
                                           @Param("end") String end);

    @Query(value = "SELECT COUNT(*) FROM VISITS V WHERE V.PATIENT_ID NOT IN( " +
            "SELECT P.ID FROM PATIENTS P WHERE CREATED_AT >= (:beginning) " +
            "AND CREATED_AT <= (:end)" +
            ");"
            ,nativeQuery = true)
    Integer findVisitsWithComplexQueryCount(@Param("beginning") String beginning,
                                           @Param("end") String end);
}
