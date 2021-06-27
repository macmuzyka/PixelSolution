package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.PractitionerCSVHandler;
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
public class PractitionerFile implements CSVService {

    PractitionerRepository practitionerRepository;

    PractitionerFile(final PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<Practitioner> practitioners = PractitionerCSVHandler.fromCSVFileToPractitioners(file.getInputStream());
            practitionerRepository.saveAll(practitioners);
        } catch (IOException ioe) {
            System.out.println("Failed to get practitioner csv data from file: " + ioe.getMessage());
        }
    }
}
