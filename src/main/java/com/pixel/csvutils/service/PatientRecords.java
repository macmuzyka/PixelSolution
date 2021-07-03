package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PatientFile;
import com.pixel.model.Patient;
import com.pixel.model.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Service
public class PatientRecords implements CSVPersistence {

    private final PatientRepository patientRepository;
    private final PatientFile patientFile;

    PatientRecords(final PatientRepository repository, final PatientFile patientFile) {
        this.patientRepository = repository;
        this.patientFile = patientFile;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Patient> patients = patientFile.fromFileToRecordsList(file.getInputStream());
            patientRepository.saveAll(patients);
        } catch (IOException ioe) {
            System.out.println("Failed to get patient csv data from file: " + ioe.getMessage());
        }
    }
}
