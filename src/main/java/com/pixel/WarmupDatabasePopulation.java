package com.pixel;

import com.pixel.advice.IllegalPersistStrategy;
import com.pixel.csvutils.handler.PatientFile;
import com.pixel.csvutils.handler.PatientToPractitionerFile;
import com.pixel.csvutils.handler.PractitionerFile;
import com.pixel.csvutils.handler.VisitFile;
import com.pixel.csvutils.service.*;
import com.pixel.model.repository.PatientRepository;
import com.pixel.model.repository.PatientToPractitionerRepository;
import com.pixel.model.repository.PractitionerRepository;
import com.pixel.model.repository.VisitRepository;
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

    private static final String baseResourcesPath = "src/main/resources/";
    public static final String csvFileExtension = ".csv";
    private static final String[] filesNames = {"patients", "practitioners", "visits", "patient2practitioner"};

    private final PatientToPractitionerRepository patientToPractitionerRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final PractitionerRepository practitionerRepository;

    private final PatientFile patientFile;
    private final VisitFile visitFile;
    private final PractitionerFile practitionerFile;
    private final PatientToPractitionerFile patientToPractitionerFile;

    WarmupDatabasePopulation(final PatientToPractitionerRepository patientToPractitionerRepository,
                             final PatientRepository patientRepository,
                             final VisitRepository visitRepository,
                             final PractitionerRepository practitionerRepository,
                             final PatientFile patientFile,
                             final VisitFile visitFile,
                             final PractitionerFile practitionerFile,
                             final PatientToPractitionerFile patientToPractitionerFile) {
        this.patientToPractitionerRepository = patientToPractitionerRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.practitionerRepository = practitionerRepository;
        this.patientFile = patientFile;
        this.visitFile = visitFile;
        this.practitionerFile = practitionerFile;
        this.patientToPractitionerFile = patientToPractitionerFile;
    }

    @Override
    public void onApplicationEvent(final ApplicationStartedEvent applicationStartedEvent) {
        int step = 1;
        final PersistStrategyResolver resolver = new PersistStrategyResolver(
                patientFile, patientRepository,
                visitFile, visitRepository,
                practitionerFile, practitionerRepository,
                patientToPractitionerFile, patientToPractitionerRepository);

        File currentFile;
        Path path;
        byte[] bytes;

        logger.info("Attempting to populate database in warmup application started event...");
        try {
            int filesProgress = 1;
            for (String file : filesNames) {
                logger.info(currentFile(file));

                currentFile = new File(baseResourcesPath + file + csvFileExtension);
                path = Paths.get(currentFile.getPath());

                CSVPersistence tempPersistence= resolver.resolve(file);

                bytes = Files.readAllBytes(path);
                tempPersistence.save(new MockMultipartFile(currentFile.getName(), bytes));

                logger.info(progressionOfFilePersistence(file));
                logger.info(progressFeedback(filesProgress));

                filesProgress++;
                step++;
            }
        } catch (IOException ioe) {
            logger.error("Error populating database at step: " + step);
            logger.info(ioe.getMessage());
        } catch (IllegalPersistStrategy ips) {
            logger.error(ips.getMessage());
        }
        logger.info("All persistence actions completed.");
    }

    private String currentFile(final String file) {
        return "File: " + file + ".";
    }

    private String progressionOfFilePersistence(final String file) {
        return "Persistence of file " + file + " completed.";
    }

    private String progressFeedback(final int filesSoFar) {
        return ((double) filesSoFar / filesNames.length) * 100 + "% completed";
    }
}
