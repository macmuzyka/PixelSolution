package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PatientCSVHandler;
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
public class PatientFile implements CSVService {

    private final PatientRepository patientRepository;

    PatientFile(final PatientRepository repository) {
        this.patientRepository = repository;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<Patient> patients = PatientCSVHandler.fromCSVFileToPatients(file.getInputStream());
            patientRepository.saveAll(patients);
        } catch (IOException ioe) {
            System.out.println("Failed to get patient csv data from file: " + ioe.getMessage());
        }
    }
}
