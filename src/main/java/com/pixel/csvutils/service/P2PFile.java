package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PatientToPractitionerCSVHandler;
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
public class P2PFile implements CSVService {
    PatientToPractitionerRepository patientToPractitionerRepository;

    P2PFile(final PatientToPractitionerRepository patientToPractitionerRepository) {
        this.patientToPractitionerRepository = patientToPractitionerRepository;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<PatientToPractitioner> p2pList = PatientToPractitionerCSVHandler.fromCSVFileToPractitioners(file.getInputStream());
            patientToPractitionerRepository.saveAll(p2pList);
        } catch (IOException ioe) {
            System.out.println("Failed to get patient to practitioner csv data from file: " + ioe.getMessage());
        }
    }
}
