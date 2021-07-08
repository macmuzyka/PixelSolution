package com.pixel;

import com.pixel.advice.IllegalPersistStrategy;
import com.pixel.csvutils.handler.PatientFile;
import com.pixel.csvutils.handler.PatientToPractitionerFile;
import com.pixel.csvutils.handler.PractitionerFile;
import com.pixel.csvutils.handler.VisitFile;
import com.pixel.csvutils.service.CSVPersistence;
import com.pixel.csvutils.service.PersistStrategyResolver;
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
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Component
class WarmupDatabasePopulation implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WarmupDatabasePopulation.class);

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
        final PersistStrategyResolver resolver = new PersistStrategyResolver(
                patientFile, patientRepository,
                visitFile, visitRepository,
                practitionerFile, practitionerRepository,
                patientToPractitionerFile, patientToPractitionerRepository);

        CSVFileNameProvider provider = new CSVFileNameProvider();
        List<String> filesNames = provider.execute();

        File currentFile;
        Path path;
        byte[] bytes;
        CSVPersistence tempPersistence;
        int filesProgress = 1;

        boolean failureOnPersist = false;

        logger.info("Attempting to populate database in warmup application started event...");
        try {
            for (String file : filesNames) {
                logger.info(currentFile(file));

                currentFile = new File(file);
                path = Paths.get(currentFile.getPath());

                tempPersistence = resolver.resolve(file);

                bytes = Files.readAllBytes(path);
                tempPersistence.save(new MockMultipartFile(currentFile.getName(), bytes));

                logger.info(progressionOfFilePersistence(file));
                logger.info(progressFeedback(filesProgress, filesNames));

                filesProgress++;
            }
        } catch (IOException ioe) {
            logger.error("Error populating database at step: " + filesProgress);
            logger.info(ioe.getMessage());
        } catch (IllegalPersistStrategy ips) {
            failureOnPersist = true;
            logger.error(ips.getMessage());
        }

        if (failureOnPersist) {
            logger.error("Error persisting files to database");
        } else {
            logger.info("All persistence actions completed.");
        }

    }

    private String currentFile(final String file) {
        return "File: " + file + ".";
    }

    private String progressionOfFilePersistence(final String file) {
        return "Persistence of file " + file + " completed.";
    }

    private String progressFeedback(final int filesProgress, final List<String> listOfFiles) {
        return ((double) filesProgress / listOfFiles.size()) * 100 + "% completed.";
    }
}
