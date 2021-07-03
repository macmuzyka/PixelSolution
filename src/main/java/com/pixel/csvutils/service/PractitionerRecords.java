package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PractitionerFile;
import com.pixel.model.Practitioner;
import com.pixel.model.repository.PractitionerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Service
public class PractitionerRecords implements CSVPersistence {

    private final PractitionerRepository practitionerRepository;
    private final PractitionerFile practitionerFile;

    PractitionerRecords(final PractitionerRepository practitionerRepository, final PractitionerFile practitionerFile) {
        this.practitionerRepository = practitionerRepository;
        this.practitionerFile = practitionerFile;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Practitioner> practitioners = practitionerFile.fromFileToRecordsList(file.getInputStream());
            practitionerRepository.saveAll(practitioners);
        } catch (IOException ioe) {
            System.out.println("Failed to get practitioner csv data from file: " + ioe.getMessage());
        }
    }
}
