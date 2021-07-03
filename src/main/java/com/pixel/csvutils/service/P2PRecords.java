package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PatientToPractitionerFile;
import com.pixel.model.PatientToPractitioner;
import com.pixel.model.repository.PatientToPractitionerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Service
public class P2PRecords implements CSVPersistence {
    private final PatientToPractitionerRepository patientToPractitionerRepository;
    private final PatientToPractitionerFile patientToPractitionerFile;

    P2PRecords(final PatientToPractitionerRepository patientToPractitionerRepository,
               final PatientToPractitionerFile patientToPractitionerFile) {
        this.patientToPractitionerRepository = patientToPractitionerRepository;
        this.patientToPractitionerFile = patientToPractitionerFile;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<PatientToPractitioner> p2pList = patientToPractitionerFile.fromFileToRecordsList(file.getInputStream());
            patientToPractitionerRepository.saveAll(p2pList);
        } catch (IOException ioe) {
            System.out.println("Failed to get patient to practitioner csv data from file: " + ioe.getMessage());
        }
    }
}
