package com.pixel.csvutils.service;

import com.pixel.advice.IllegalPersistStrategy;
import com.pixel.csvutils.handler.PatientFile;
import com.pixel.csvutils.handler.PatientToPractitionerFile;
import com.pixel.csvutils.handler.PractitionerFile;
import com.pixel.csvutils.handler.VisitFile;
import com.pixel.model.repository.PatientRepository;
import com.pixel.model.repository.PatientToPractitionerRepository;
import com.pixel.model.repository.PractitionerRepository;
import com.pixel.model.repository.VisitRepository;

/**
 * Created by Maciej Muzyka
 * on 01.07.2021
 */

public class PersistStrategyResolver {
    private final PatientToPractitionerRepository patientToPractitionerRepository;
    private final PatientToPractitionerFile patientToPractitionerFile;
    private final PatientRepository patientRepository;
    private final PatientFile patientFile;
    private final PractitionerRepository practitionerRepository;
    private final PractitionerFile practitionerFile;
    private final VisitRepository visitRepository;
    private final VisitFile visitFile;

    public PersistStrategyResolver(
            final PatientFile patientFile,
            final PatientRepository patientRepository,
            final VisitFile visitFile,
            final VisitRepository visitRepository,
            final PractitionerFile practitionerFile,
            final PractitionerRepository practitionerRepository,
            final PatientToPractitionerFile patientToPractitionerFile,
            final PatientToPractitionerRepository patientToPractitionerRepository) {
        this.patientToPractitionerRepository = patientToPractitionerRepository;
        this.patientToPractitionerFile = patientToPractitionerFile;
        this.patientRepository = patientRepository;
        this.patientFile = patientFile;
        this.practitionerRepository = practitionerRepository;
        this.practitionerFile = practitionerFile;
        this.visitRepository = visitRepository;
        this.visitFile = visitFile;
    }

    public CSVPersistence resolve(String file) {
        switch (file) {
            case "src/main/resources/patient2practitioner.csv":
                return new P2PRecords(patientToPractitionerRepository, patientToPractitionerFile);
            case "src/main/resources/patients.csv":
                return new PatientRecords(patientRepository, patientFile);
            case "src/main/resources/practitioners.csv":
                return new PractitionerRecords(practitionerRepository, practitionerFile);
            case "src/main/resources/visits.csv":
                return new VisitRecords(visitRepository, visitFile);
            default:
                throw new IllegalPersistStrategy("Persist strategy not resolved!");
        }
    }
}
