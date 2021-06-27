package com.pixel;

import com.pixel.csvutils.service.PatientFile;
import com.pixel.csvutils.service.P2PFile;
import com.pixel.csvutils.service.PractitionerFile;
import com.pixel.csvutils.service.VisitsFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Component
class WarmupDatabasePopulation implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WarmupDatabasePopulation.class);
    private final PatientFile patientService;
    private final PractitionerFile practitionerService;
    private final VisitsFile visitService;
    private final P2PFile patientToPractitionerService;

    WarmupDatabasePopulation(final PatientFile patientService,
                             final PractitionerFile practitionerService,
                             final VisitsFile visitService,
                             final P2PFile patientToPractitionerService) {
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.visitService = visitService;
        this.patientToPractitionerService = patientToPractitionerService;
    }

    @Override
    public void onApplicationEvent(final ApplicationStartedEvent applicationStartedEvent) {
        File patientsCSVFile = new File("src/main/resources/patients.csv");
        Path patientsCSVFilePath = Paths.get(patientsCSVFile.getPath());

        File practitionersCSVFile = new File("src/main/resources/practitioners.csv");
        Path practitionersCSVFilePath = Paths.get(practitionersCSVFile.getPath());

        File visitsCSVFile = new File("src/main/resources/visits.csv");
        Path visitsCSVFilePath = Paths.get(visitsCSVFile.getPath());

        File p2pCSVFile = new File("src/main/resources/patient2practitioner.csv");
        Path p2pCSVFilePath = Paths.get(p2pCSVFile.getPath());

        byte[] tempByteArray;

        try {
            logger.info("Application start, attempting to insert data from CSV files to database.");
            tempByteArray = Files.readAllBytes(patientsCSVFilePath);
            patientService.save(new MockMultipartFile(patientsCSVFile.getName(), tempByteArray));

            tempByteArray = Files.readAllBytes(practitionersCSVFilePath);
            practitionerService.save(new MockMultipartFile(practitionersCSVFile.getName(), tempByteArray));

            tempByteArray = Files.readAllBytes(visitsCSVFilePath);
            visitService.save(new MockMultipartFile(visitsCSVFile.getName(), tempByteArray));

            tempByteArray = Files.readAllBytes(p2pCSVFilePath);
            patientToPractitionerService.save(new MockMultipartFile(p2pCSVFile.getName(), tempByteArray));

            logger.info("Initial database population successful.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
